package com.charter.interpretme.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.charter.interpretme.rest.entity.ServiceRequest;

@Repository
public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, String> {

    List<ServiceRequest> findByClientId(String clientId);

    List<ServiceRequest> findByVolunteerId(String volunteerId);
}
