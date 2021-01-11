package com.dell.appliances.service;

import com.dell.appliances.common.Constants;
import com.dell.appliances.common.EmailConfigUtil;
import com.dell.appliances.common.StringUtils;
import com.dell.appliances.dto.ApplianceReservationPayload;
import com.dell.appliances.exceptions.ApplianceException;
import com.dell.appliances.exceptions.Error;
import com.dell.appliances.model.ApplianceDetails;
import com.dell.appliances.service.interfaces.IAlertingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;



/*
 *    Created by rajbar[rohit.rajbanshi@dell.com] on Thursday 12/10/2020
 *
 */
@Service
public class AlertingService implements IAlertingService {

    public static final Logger LOG = LogManager.getLogger(AlertingService.class);

    private static Properties props = new Properties();
    private static List<String> emailList = new ArrayList<>();
    private static String fromEmail;

    @PostConstruct
    private void init(){
        LOG.info(EmailConfigUtil.getEmailProperties().getHost());
        props.put("mail.smtp.host",EmailConfigUtil.getEmailProperties().getHost());
        props.put("mail.smtp.port", EmailConfigUtil.getEmailProperties().getPort());
        props.put("mail.smtp.connectiontimeout",EmailConfigUtil.getEmailProperties().getTimeout());

        fromEmail = EmailConfigUtil.getEmailProperties().getFrom();
        emailList.addAll(EmailConfigUtil.getEmailProperties().getTo());
    }


    @Override
    public void sendReservationEmail(ApplianceDetails applianceDetails) throws ApplianceException {
        String style = "table, th, td {\n" +
                "  border: 1px solid black;\n" +
                "border-collapse: collapse;"+
                "padding:5px;"+
                "}";
        String content = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<style>\n" +
                style +
                "</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<h2>Appliance Reservation Details:</h2>"+
                "<table>\n" +
                "  <tr>\n" +
                "    <th>Appliance Name</th>\n" +
                "    <th>Appliance Model</th>\n" +
                "    <th>UoM Name</th>\n" +
                "    <th>Purpose</th>\n" +
                "    <th>Assignee Name</th>\n" +
                "    <th>Assignee Email</th>\n" +
                "    <th>Start Date</th>\n" +
                "    <th>End Date</th>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>"+applianceDetails.getApplianceName()+"</td>\n" +
                "    <td>"+applianceDetails.getApplianceModel().getVal()+"</td>\n" +
                "    <td>"+applianceDetails.getUnitOfManagement().getName()+"</td>\n" +
                "    <td>"+applianceDetails.getAppliancePossession().getPurpose().getVal()+"</td>\n" +
                "    <td>"+applianceDetails.getAppliancePossession().getAssigneeName()+"</td>\n" +
                "    <td>"+applianceDetails.getAppliancePossession().getAssigneeEmail()+"</td>\n" +
                "    <td>"+StringUtils.extractDate(applianceDetails.getAppliancePossession().getStartDate())+"</td>\n" +
                "    <td>"+StringUtils.extractDate(applianceDetails.getAppliancePossession().getEndDate())+"</td>\n" +
                "  </tr>\n" +
                "</table>"+
                "<a href=\""+ Constants.APP_URL +"\">IDPA Appliance Reservation Tool</a>";
        String subject = applianceDetails.getAppliancePossession().getAssigneeName()+" has reserved "+
                applianceDetails.getApplianceName()+" [ Model:"+applianceDetails.getApplianceModel().getVal()+" ] appliance "+
                "for the purpose of "+applianceDetails.getAppliancePossession().getPurpose().getVal();
        sendEmail(subject,content);
    }

    @Override
    public void sendReleaseEmail(ApplianceDetails applianceDetails) throws ApplianceException {
        String style = "table, th, td {\n" +
                "  border: 1px solid black;\n" +
                "border-collapse: collapse;"+
                "padding:5px;"+
                "}";
        String content = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<style>\n" +
                style +
                "</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<h2>Appliance Details:</h2>"+
                "<table>\n" +
                "  <tr>\n" +
                "    <th>Appliance Name</th>\n" +
                "    <th>Appliance Model</th>\n" +
                "    <th>UoM Name</th>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>"+applianceDetails.getApplianceName()+"</td>\n" +
                "    <td>"+applianceDetails.getApplianceModel().getVal()+"</td>\n" +
                "    <td>"+applianceDetails.getUnitOfManagement().getName()+"</td>\n" +
                "  </tr>\n" +
                "</table>"+
                "<a href=\""+ Constants.APP_URL +"\">IDPA Appliance Reservation Tool</a>";
        String subject = applianceDetails.getApplianceName()+" [ Model:"+applianceDetails.getApplianceModel().getVal()+" ] appliance is available for reservation";
        sendEmail(subject,content);
    }

    @Override
    public void sendExpirationNotifyEmail(ApplianceDetails applianceDetails) throws ApplianceException {
        String style = "table, th, td {\n" +
                "  border: 1px solid black;\n" +
                "border-collapse: collapse;"+
                "padding:5px;"+
                "}";
        String content = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<style>\n" +
                style +
                "</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<p>Your appliance reservation is about to expire on "+ StringUtils.extractDate(applianceDetails.getAppliancePossession().getEndDate())+" by the end of the day. Please extend your reservation or the appliance will be released automatically</p>"+
                "<h2>Appliance Details:</h2>"+
                "<table>\n" +
                "  <tr>\n" +
                "    <th>Appliance Name</th>\n" +
                "    <th>Appliance Model</th>\n" +
                "    <th>UoM Name</th>\n" +
                "    <th>Purpose</th>\n" +
                "    <th>Assignee Name</th>\n" +
                "    <th>Assignee Email</th>\n" +
                "    <th>Start Date</th>\n" +
                "    <th>End Date</th>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>"+applianceDetails.getApplianceName()+"</td>\n" +
                "    <td>"+applianceDetails.getApplianceModel().getVal()+"</td>\n" +
                "    <td>"+applianceDetails.getUnitOfManagement().getName()+"</td>\n" +
                "    <td>"+applianceDetails.getAppliancePossession().getPurpose().getVal()+"</td>\n" +
                "    <td>"+applianceDetails.getAppliancePossession().getAssigneeName()+"</td>\n" +
                "    <td>"+applianceDetails.getAppliancePossession().getAssigneeEmail()+"</td>\n" +
                "    <td>"+StringUtils.extractDate(applianceDetails.getAppliancePossession().getStartDate())+"</td>\n" +
                "    <td>"+StringUtils.extractDate(applianceDetails.getAppliancePossession().getEndDate())+"</td>\n" +
                "  </tr>\n" +
                "</table>"+
                "<a href=\""+ Constants.APP_URL +"\">IDPA Appliance Reservation Tool</a>";
        String subject = applianceDetails.getApplianceName()+" [ Model:"+applianceDetails.getApplianceModel().getVal()+" ] appliance reservation is about to expire!";
        List<String> toList = new ArrayList<>();
        if(Objects.nonNull(applianceDetails.getAppliancePossession().getAssigneeEmail()) &&  !applianceDetails.getAppliancePossession().getAssigneeEmail().isEmpty())
            toList.add(applianceDetails.getAppliancePossession().getAssigneeEmail());
        if(!toList.isEmpty())
            sendEmail(subject,content,toList);
    }

    @Override
    public void sendApplianceAddEmail(ApplianceReservationPayload applianceReservationPayload,ApplianceDetails applianceDetails) throws ApplianceException {

    }

    @Override
    public void sendEmail(String subject,String content) throws ApplianceException {
        sendEmail(subject,content,emailList);
    }

    @Override
    public void sendEmail(String subject,String content,List<String> emailList) throws ApplianceException {
        Session session = Session.getInstance(props, null);
        try {
            MimeMessage message = new MimeMessage(session);
            InternetAddress[] emailListArray = new InternetAddress[emailList.size()];
            for (int i =0; i < emailList.size(); i++)
                emailListArray[i] = new InternetAddress(emailList.get(i));

            message.setFrom(new InternetAddress(fromEmail));
            message.addRecipients(Message.RecipientType.TO, emailListArray);
            message.setSubject(subject);
            message.setContent(content,"text/html");
            // Send message
            Transport.send(message);
            LOG.info("E-Mail sent successfully");
        }catch( javax.mail.SendFailedException  mx) {
            StringBuilder errorSB = null;

            if(mx.getInvalidAddresses() != null) {
                errorSB = new StringBuilder();
                for(Address email: mx.getInvalidAddresses()) {
                    errorSB.append(email.toString());
                    errorSB.append(", ");
                }
                LOG.error("Invalid Address Found: "+ errorSB);
            }

            if(mx.getValidSentAddresses() != null) {
                errorSB = new StringBuilder();
                for(Address email: mx.getValidSentAddresses()) {
                    errorSB.append(email.toString());
                    errorSB.append(", ");
                }
                LOG.error("Email sent to valid address: "+ errorSB);
            }

            if(mx.getValidUnsentAddresses() != null) {
                errorSB = new StringBuilder();
                for(Address email: mx.getValidUnsentAddresses()) {
                    errorSB.append(email.toString());
                    errorSB.append(", ");
                }
                LOG.error("Email not sent to valid address: "+ errorSB);
            }
            LOG.error("Email Send failed",mx);
            throw new ApplianceException(Error.EMAIL_FAILED,mx);
        } catch (MessagingException e) {
            LOG.error("Email Send failed"+e);
            throw new ApplianceException(Error.EMAIL_FAILED,e);
        }
    }

}
