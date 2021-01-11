package com.dell.appliances.repo;

import com.dell.appliances.model.AppliancePossession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 *    Created by rajbar[rohit.rajbanshi@dell.com] on Friday 12/4/2020
 *
 */
@Repository
public interface AppliancePossessionRepository extends JpaRepository<AppliancePossession, Long> {
}