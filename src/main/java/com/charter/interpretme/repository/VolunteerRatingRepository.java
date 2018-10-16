package com.charter.interpretme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.charter.interpretme.rest.entity.VolunteerRating;

@Repository
public interface VolunteerRatingRepository extends JpaRepository<VolunteerRating, String> {
}
