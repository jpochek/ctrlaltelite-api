package com.charter.interpretme;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.charter.interpretme.repository.VolunteerProfileRepository;
import com.charter.interpretme.rest.entity.ServiceRequest;
import com.charter.interpretme.rest.entity.VolunteerProfile;

@Component
public class VolunteerNotificationSender {

    @Autowired
    private VolunteerProfileRepository volunteerProfileRepository;
    @Autowired
    private VolunteerNotificationEmailSender volunteerNotificationEmailSender;
    @Autowired
    private TaskExecutor taskExecutor;

    public void sendVolunteerNotificationAsync(ServiceRequest serviceRequest) {
        taskExecutor.execute(() -> {
            sendVolunteerNotification(serviceRequest);
        });
    }

    public void sendVolunteerNotification(ServiceRequest serviceRequest) {
        List<VolunteerProfile> volunteers = volunteerProfileRepository
                .findByStreetAddress1AndStateAndPostalCodeAndCity(
                        serviceRequest.getStreetAddress(), serviceRequest.getState(), serviceRequest.getZipCode(),
                        serviceRequest.getCity());

        if (!CollectionUtils.isEmpty(volunteers)) {
            List<VolunteerProfile> filteredVolunteers = volunteers.stream()
                    .filter(v -> v.getLanguages().contains(serviceRequest.getLanguageFrom()))
                    .filter(v -> v.getLanguages().contains(serviceRequest.getLanguageTo()))
                    .collect(Collectors.toList());

            volunteerNotificationEmailSender.sendEmailToVolunteers(serviceRequest, filteredVolunteers);
        }
    }
}
