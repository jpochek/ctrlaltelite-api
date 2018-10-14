package com.charter.interpretme;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
    @Autowired
    private VolunteerNotificationSender volunteerNotificationSender;

    @GetMapping
    public List<ServiceRequest> getServiceRequests() {
        return serviceRequestRepository.findAll();
    }

    @GetMapping("/client/{clientId}")
    public List<ServiceRequest> clientSpecialRequests(@PathVariable String clientId) {
        return serviceRequestRepository.findByClientId(clientId);
    }

    @GetMapping("/volunteer/{volunteerId}")
    public List<ServiceRequest> volunteerSpecialRequests(@PathVariable String volunteerId) {
        return serviceRequestRepository.findByVolunteerId(volunteerId);
    }

    @PostMapping
    public ServiceRequest create(@RequestBody ServiceRequest serviceRequest, HttpServletRequest request)
            throws MalformedURLException {

        URL url = new URL(request.getRequestURL().toString());

        ServiceRequest response = serviceRequestRepository.save(new ServiceRequest(
                serviceRequest.getClientId(),
                null,
                serviceRequest.getLanguageTo(),
                serviceRequest.getLanguageFrom(),
                serviceRequest.getPriority(),
                serviceRequest.getZipCode(),
                serviceRequest.getState(),
                serviceRequest.getCity(),
                serviceRequest.getStreetAddress(),
                serviceRequest.getInPerson(),
                serviceRequest.getCategory(),
                serviceRequest.getDescription(),
                serviceRequest.getAppointmentFrom(),
                serviceRequest.getAppointmentTo(),
                ServiceRequest.Status.Pending
        ));
        volunteerNotificationSender.sendVolunteerNotificationAsync(response, url);
        return response;
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
                serviceRequest.getCity(),
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
