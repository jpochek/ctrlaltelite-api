package com.charter.interpretme;

import java.net.MalformedURLException;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.charter.interpretme.repository.ClientProfileRepository;
import com.charter.interpretme.repository.ServiceRequestRepository;
import com.charter.interpretme.repository.VolunteerProfileRepository;
import com.charter.interpretme.rest.entity.ClientProfile;
import com.charter.interpretme.rest.entity.ServiceRequest;
import com.charter.interpretme.rest.entity.VolunteerProfile;
import com.charter.interpretme.utils.EncryptionUtil;

@RestController
@RequestMapping("/api/confirm")
public class ConfirmationControlller {
    @Autowired
    private ConfirmationLinkGenerator confirmationLinkGenerator;
    @Autowired
    private VolunteerNotificationEmailSender mainSender;
    @Autowired
    private ClientProfileRepository clientProfileRepository;
    @Autowired
    private VolunteerProfileRepository volunteerProfileRepository;
    @Autowired
    private ServiceRequestRepository serviceRequestRepository;
    @Autowired
    private ClientConfirmationSender clientConfirmationSender;

    @GetMapping("/{confirmationId}")
    public String confirm(@PathVariable String confirmationId) throws UnknownHostException, MalformedURLException {
        System.out.println(">>>>>>>>>>>>>>" + confirmationId);
        String decryptedConfirmationId = EncryptionUtil.decrypt(confirmationId);
        String[] confirmationIdParts = decryptedConfirmationId.split("-");
        String volunteerId = confirmationIdParts[0];
        String clientId = confirmationIdParts[1];
        String serviceRequestId = confirmationIdParts[2];
        ClientProfile clientProfile = clientProfileRepository.findOne(clientId);
        VolunteerProfile volunteerProfile = volunteerProfileRepository.findOne(volunteerId);
        ServiceRequest serviceRequest = serviceRequestRepository.findOne(serviceRequestId);
        if(!serviceRequest.getStatus().equals(ServiceRequest.Status.Completed)) {
            updateServiceRequest(serviceRequest, volunteerId);
            clientConfirmationSender.sendClientConfirmationAsync(serviceRequest, clientProfile, volunteerProfile);
            return "You are confirmed";
        }else{
            return "You are not confirmed";
        }

    }

    private void updateServiceRequest(ServiceRequest serviceRequest, String volunteerId) {
        serviceRequest.setStatus(ServiceRequest.Status.Completed);
        serviceRequest.setVolunteerId(volunteerId);
        serviceRequestRepository.save(serviceRequest);
    }

}
