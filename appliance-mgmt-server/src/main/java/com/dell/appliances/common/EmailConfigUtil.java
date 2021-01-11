package com.dell.appliances.common;

import com.dell.appliances.model.EmailConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.javaprop.JavaPropsMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

/*
 *    Created by rajbar[rohit.rajbanshi@dell.com] on Wednesday 12/16/2020
 *
 */
@Component
public class EmailConfigUtil {

    public static final Logger LOG = LogManager.getLogger(EmailConfigUtil.class);

    private static EmailConfig emailConfig;

    @Value("${email.prop.path}")
    private String emailPropPath;

    @Autowired
    ResourceLoader resourceLoader;

    @Value("${spring.profiles.active}")
    private String profile;

    @PostConstruct
    private void init() throws IOException {
        if(Objects.nonNull(emailPropPath)){
            File file = null;
            if(profile.equals("dev")){
                file = resourceLoader.getResource("classpath:"+emailPropPath).getFile();
            }else{
                file = new File(emailPropPath);
            }
            LOG.info("Fetching email properties from:"+emailPropPath);
            ObjectMapper mapper = new ObjectMapper();
            emailConfig = mapper.readValue(file, EmailConfig.class);
        }
    }

    public static EmailConfig getEmailProperties() {
        return emailConfig;
    }

    public static void setEmailProperties(EmailConfig emailConfig) {
        EmailConfigUtil.emailConfig = emailConfig;
    }
}
