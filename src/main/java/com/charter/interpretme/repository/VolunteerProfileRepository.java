package com.charter.interpretme.repository;

import com.charter.interpretme.rest.entity.VolunteerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VolunteerProfileRepository extends JpaRepository<VolunteerProfile, String> {
    VolunteerProfile findByUsername(String username);
    VolunteerProfile findByEmailAddress(String emailAddress);
}
