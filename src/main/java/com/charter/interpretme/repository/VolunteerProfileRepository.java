package com.charter.interpretme.repository;

import com.charter.interpretme.rest.entity.VolunteerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VolunteerProfileRepository extends JpaRepository<VolunteerProfile, String>, JpaSpecificationExecutor {
    VolunteerProfile findByUsername(String username);

    VolunteerProfile findByEmailAddress(String emailAddress);

    List<VolunteerProfile> findByStreetAddress1AndStateAndPostalCodeAndCity(String streetAddress, String state, String postalCode,String city);
}
