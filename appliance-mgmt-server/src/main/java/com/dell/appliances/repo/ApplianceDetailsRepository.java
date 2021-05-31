package com.dell.appliances.repo;

import com.dell.appliances.dto.ApplianceCountByModel;
import com.dell.appliances.model.ApplianceDetails;
import com.dell.appliances.service.interfaces.ICountByGeneration;
import com.dell.appliances.service.interfaces.ICountByLocation;
import com.dell.appliances.service.interfaces.ICountByModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/*
 *    Created by rajbar[rohit.rajbanshi@dell.com] on Friday 12/4/2020
 *
 */
@Repository
public interface ApplianceDetailsRepository extends JpaRepository<ApplianceDetails, Long> {

    Optional<ApplianceDetails> findByApplianceName(String applianceName);

    @Query("SELECT c.applianceModel as model, COUNT(c.applianceModel) as totalCount FROM ApplianceDetails AS c GROUP BY c.applianceModel")
    List<ICountByModel> getTotalCountByModel();

    @Query("SELECT c.location as location, COUNT(c.location) as totalCount FROM ApplianceDetails AS c WHERE c.applianceModel != \'LOCAL\' GROUP BY c.location")
    List<ICountByLocation> getTotalCountByLocation();

    @Query("SELECT c.generation as generation, COUNT(c.generation) as totalCount FROM ApplianceDetails AS c WHERE c.applianceModel != \'LOCAL\' GROUP BY c.generation")
    List<ICountByGeneration> getTotalCountByGeneration();

    @Query("SELECT c.location as location, COUNT(c.location) as totalCount FROM ApplianceDetails AS c WHERE c.applianceModel = \'LOCAL\' GROUP BY c.location")
    List<ICountByLocation> getTotalCountByLocationEsxi();
}
