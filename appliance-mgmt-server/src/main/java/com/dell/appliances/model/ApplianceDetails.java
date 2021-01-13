package com.dell.appliances.model;

import com.dell.appliances.model.enumerations.*;

import javax.persistence.*;

/*
 *    Created by rajbar[rohit.rajbanshi@dell.com] on Friday 12/4/2020
 *
 */
@Entity
@Table(name="appliance_details")
public class ApplianceDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "appliance_name",nullable=false)
    private String applianceName;

    @Column(name = "model",nullable = false)
    @Enumerated(EnumType.STRING)
    private ApplianceModel applianceModel;

    @Column(name = "acm_ip")
    private String acmIP;

    @Column(name = "shareable",nullable=false)
    private Boolean canBeShared;

    @Column(name = "acm_password")
    private String acmPassword;

    @Column(name = "appliance_state")
    private String applianceState;

    @Column(name = "owner_name")
    private String ownerName;

    @Column(name = "email")
    private String email;

    @Column(name = "location",nullable=false)
    @Enumerated(EnumType.STRING)
    private Location location;

    @Column(name = "generation",nullable=false)
    @Enumerated(EnumType.STRING)
    private Generation generation;

    @Column(name = "purpose",nullable=false)
    @Enumerated(EnumType.STRING)
    private Purpose purpose;

    @Column(name="possession_status",nullable=false)
    @Enumerated(EnumType.STRING)
    private AppliancePossessionStatus appliancePossessionStatus;

    @Lob
    @Column(name="configuration",nullable=true)
    private String configuration;

    @ManyToOne
    @JoinColumn(name="fk_possession_id")
    private AppliancePossession appliancePossession;

    @ManyToOne
    @JoinColumn(name="fk_uom_id")
    private UnitOfManagement unitOfManagement;

    public ApplianceDetails() {}

    public ApplianceDetails(String applianceName, ApplianceModel applianceModel, Boolean canBeShared) {
        this.applianceName = applianceName;
        this.applianceModel = applianceModel;
        this.canBeShared = canBeShared;
        this.appliancePossessionStatus = AppliancePossessionStatus.AVAILABLE;
    }

    public ApplianceDetails(String applianceName, ApplianceModel applianceModel, Boolean canBeShared, String acmIP, String acmPassword, String applianceState) {
        this.applianceName = applianceName;
        this.applianceModel = applianceModel;
        this.canBeShared = canBeShared;
        this.acmIP = acmIP;
        this.acmPassword = acmPassword;
        this.applianceState = applianceState;
        this.appliancePossessionStatus = AppliancePossessionStatus.AVAILABLE;
    }

    public String getApplianceName() {
        return applianceName;
    }

    public void setApplianceName(String applianceName) {
        this.applianceName = applianceName;
    }

    public ApplianceModel getApplianceModel() {
        return applianceModel;
    }

    public void setApplianceModel(ApplianceModel applianceModel) {
        this.applianceModel = applianceModel;
    }

    public Boolean getCanBeShared() {
        return canBeShared;
    }

    public void setCanBeShared(Boolean canBeShared) {
        this.canBeShared = canBeShared;
    }

    public String getAcmIP() {
        return acmIP;
    }

    public void setAcmIP(String acmIP) {
        this.acmIP = acmIP;
    }

    public String getAcmPassword() {
        return acmPassword;
    }

    public void setAcmPassword(String acmPassword) {
        this.acmPassword = acmPassword;
    }

    public String getApplianceState() {
        return applianceState;
    }

    public void setApplianceState(String applianceState) {
        this.applianceState = applianceState;
    }

    public AppliancePossession getAppliancePossession() {
        return appliancePossession;
    }

    public void setAppliancePossession(AppliancePossession appliancePossession) {
        this.appliancePossession = appliancePossession;
    }

    public UnitOfManagement getUnitOfManagement() {
        return unitOfManagement;
    }

    public void setUnitOfManagement(UnitOfManagement unitOfManagement) {
        this.unitOfManagement = unitOfManagement;
    }

    public AppliancePossessionStatus getAppliancePossessionStatus() {
        return appliancePossessionStatus;
    }

    public void setAppliancePossessionStatus(AppliancePossessionStatus appliancePossessionStatus) {
        this.appliancePossessionStatus = appliancePossessionStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConfiguration() {
        return configuration;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Generation getGeneration() {
        return generation;
    }

    public void setGeneration(Generation generation) {
        this.generation = generation;
    }

    public Purpose getPurpose() {
        return purpose;
    }

    public void setPurpose(Purpose purpose) {
        this.purpose = purpose;
    }

    @Override
    public String toString() {
        return "ApplianceDetails{" +
                "id=" + id +
                ", applianceName='" + applianceName + '\'' +
                ", applianceModel=" + applianceModel +
                ", acmIP='" + acmIP + '\'' +
                ", canBeShared=" + canBeShared +
                ", acmPassword='" + acmPassword + '\'' +
                ", applianceState='" + applianceState + '\'' +
                ", appliancePossessionStatus=" + appliancePossessionStatus +
                ", appliancePossession=" + appliancePossession +
                ", unitOfManagement=" + unitOfManagement +
                '}';
    }
}
