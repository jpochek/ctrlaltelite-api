package com.charter.interpretme;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.charter.interpretme.repository.ServiceRequestRepository;
import com.charter.interpretme.rest.entity.ServiceRequest;

@RestController
@RequestMapping("/api/servicerequests")
public class ServiceRequestController {
    @Autowired
    private ServiceRequestRepository serviceRequestRepository;

    @GetMapping
    public List<ServiceRequest> getServiceRequests() {
        return serviceRequestRepository.findAll();
    }

    @PostMapping
    public ServiceRequest create(@RequestBody ServiceRequest serviceRequest) {
        return serviceRequestRepository.save(new ServiceRequest(
                serviceRequest.getClientId(),
                null,
                serviceRequest.getLanguageTo(),
                serviceRequest.getLanguageFrom(),
                serviceRequest.getPriority(),
                serviceRequest.getZipCode(),
                serviceRequest.getState(),
                serviceRequest.getStreetAddress(),
                serviceRequest.getInPerson(),
                serviceRequest.getCategory(),
                serviceRequest.getDescription(),
                serviceRequest.getAppointmentFrom(),
                serviceRequest.getAppointmentTo(),
                ServiceRequest.Status.Pending
        ));
    }

    @PutMapping("/{serviceRequestId}")
    public ServiceRequest update(@PathVariable String serviceRequestId, @RequestBody ServiceRequest serviceRequest) {
        return serviceRequestRepository.save(new ServiceRequest(serviceRequestId,
                serviceRequest.getClientId(),
                serviceRequest.getVolunteerId(),
                serviceRequest.getLanguageTo(),
                serviceRequest.getLanguageFrom(),
                serviceRequest.getPriority(),
                serviceRequest.getZipCode(),
                serviceRequest.getState(),
                serviceRequest.getStreetAddress(),
                serviceRequest.getInPerson(),
                serviceRequest.getCategory(),
                serviceRequest.getDescription(),
                serviceRequest.getAppointmentFrom(),
                serviceRequest.getAppointmentTo(),
                serviceRequest.getStatus()
        ));
    }

    @DeleteMapping("/{serviceRequestId}")
    public void update(@PathVariable String serviceRequestId) {
        serviceRequestRepository.delete(serviceRequestId);
    }
}
