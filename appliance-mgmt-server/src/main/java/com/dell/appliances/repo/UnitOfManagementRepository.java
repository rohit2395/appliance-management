package com.dell.appliances.repo;

import com.dell.appliances.model.UnitOfManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*
 *    Created by rajbar[rohit.rajbanshi@dell.com] on Friday 12/4/2020
 *
 */
@Repository
public interface UnitOfManagementRepository extends JpaRepository<UnitOfManagement, Long> {
    Optional<UnitOfManagement> findByName(String uomName);
}
