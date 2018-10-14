package com.charter.interpretme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import com.charter.interpretme.rest.entity.ClientProfile;
import com.charter.interpretme.rest.entity.ServiceRequest;
import com.charter.interpretme.rest.entity.VolunteerProfile;

@Component
public class ClientConfirmationSender {
    @Autowired
    private TaskExecutor taskExecutor;
    @Autowired
    private ClientConfirmationEmailSender clientConfirmationEmailSender;

    public void sendClientConfirmationAsync(ServiceRequest serviceRequest, ClientProfile clientProfile,
            VolunteerProfile volunteerProfile) {
        taskExecutor.execute(() -> {
            sendClientConfirmation(serviceRequest, clientProfile, volunteerProfile);
        });
    }

    private void sendClientConfirmation(ServiceRequest serviceRequest, ClientProfile clientProfile,
            VolunteerProfile volunteerProfile) {
        clientConfirmationEmailSender.sendEmailToClient(serviceRequest, clientProfile, volunteerProfile);
    }
}
