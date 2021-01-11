package com.dell.appliances.model;

import javax.persistence.*;
import java.util.List;

/*
 *    Created by rajbar[rohit.rajbanshi@dell.com] on Friday 12/4/2020
 *
 */
@Entity
@Table(name="uom_details")
public class UnitOfManagement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="uom_name",nullable=false)
    private String name;

    @OneToMany(mappedBy = "unitOfManagement",cascade=CascadeType.ALL)
    private List<ApplianceDetails> applianceDetails;

    public UnitOfManagement() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ApplianceDetails> getApplianceDetails() {
        return applianceDetails;
    }

    public void setApplianceDetails(List<ApplianceDetails> applianceDetails) {
        this.applianceDetails = applianceDetails;
    }

    @Override
    public String toString() {
        return "UnitOfManagement{" +
                "name='" + name + '\'' +
                ", applianceDetails=" + applianceDetails +
                '}';
    }
}
