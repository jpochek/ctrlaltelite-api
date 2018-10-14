package com.charter.interpretme;

import java.net.URL;
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

    public void sendVolunteerNotificationAsync(ServiceRequest serviceRequest, URL url) {
        taskExecutor.execute(() -> {
            sendVolunteerNotification(serviceRequest, url);
        });
    }

    public void sendVolunteerNotification(ServiceRequest serviceRequest, URL url) {
        List<VolunteerProfile> volunteers = volunteerProfileRepository
                .findByPostal(serviceRequest.getZipCode());
        if (CollectionUtils.isEmpty(volunteers))
            volunteers = volunteerProfileRepository
                    .findByStateAndCity(serviceRequest.getState(), serviceRequest.getCity());
        if (CollectionUtils.isEmpty(volunteers))
            volunteers = volunteerProfileRepository.findByState(serviceRequest.getState());
        if (CollectionUtils.isEmpty(volunteers))
            volunteers = volunteerProfileRepository.findAll();

        if (!CollectionUtils.isEmpty(volunteers)) {
            List<VolunteerProfile> filteredVolunteers = volunteers.stream()
                    .filter(v -> v.getLanguages().contains(serviceRequest.getLanguageFrom()))
                    .filter(v -> v.getLanguages().contains(serviceRequest.getLanguageTo()))
                    .collect(Collectors.toList());

            volunteerNotificationEmailSender.sendEmailToVolunteers(serviceRequest, filteredVolunteers, url);
        }
    }
}
