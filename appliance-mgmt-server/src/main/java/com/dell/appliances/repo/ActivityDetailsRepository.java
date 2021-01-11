package com.dell.appliances.repo;

import com.dell.appliances.model.ActivityDetails;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*
 *    Created by rajbar[rohit.rajbanshi@dell.com] on Tuesday 12/8/2020
 *
 */
public interface ActivityDetailsRepository extends JpaRepository<ActivityDetails, Long> {

    List<ActivityDetails> findAllByOrderByDateDesc();
}
