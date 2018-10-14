package com.charter.interpretme;

import org.apache.commons.lang3.StringUtils;

import com.charter.interpretme.repository.ClientProfileRepository;
import com.charter.interpretme.repository.VolunteerProfileRepository;
import com.charter.interpretme.rest.entity.ClientProfile;
import com.charter.interpretme.rest.entity.ServiceRequest;
import com.charter.interpretme.rest.entity.VolunteerProfile;

public class CompletedServiceRequest extends ServiceRequest {
    private String volunteerName;
    private Double ratings;
    private String clientName;

    public CompletedServiceRequest(ServiceRequest serviceRequest, VolunteerProfileRepository volunteerProfileRepository,
            ClientProfileRepository clientProfileRepository) {
        super(serviceRequest.getId(),
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
        );
        if (StringUtils.isNotBlank(serviceRequest.getVolunteerId())) {
            VolunteerProfile volunteerProfile = volunteerProfileRepository.findOne(serviceRequest.getVolunteerId());
            this.volunteerName = String.join(" ", volunteerProfile.getFirstName(), volunteerProfile.getLastName());
            this.ratings = volunteerProfile.getAverageRating();

        }
        if (StringUtils.isNotBlank(serviceRequest.getClientId())) {
            ClientProfile clientProfile = clientProfileRepository.findOne(serviceRequest.getClientId());
            this.clientName = String.join(" ", clientProfile.getFirstName(), clientProfile.getLastName());
        }
    }

    public String getVolunteerName() {
        return volunteerName;
    }

    public Double getRatings() {
        return ratings;
    }

    public String getClientName() {
        return clientName;
    }
}
