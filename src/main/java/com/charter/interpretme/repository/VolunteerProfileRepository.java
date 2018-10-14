package com.charter.interpretme.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.charter.interpretme.rest.entity.VolunteerProfile;

@Repository
public interface VolunteerProfileRepository extends JpaRepository<VolunteerProfile, String>, JpaSpecificationExecutor {
    VolunteerProfile findByUsernameContainingIgnoreCase(String username);

    VolunteerProfile findByEmailAddressContainingIgnoreCase(String emailAddress);

    List<VolunteerProfile> findByPostal(String postalCode);

    List<VolunteerProfile> findByStateAndCity(String state, String city);

    List<VolunteerProfile> findByState(String state);
}
