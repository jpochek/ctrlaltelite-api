package com.charter.interpretme;

import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.charter.interpretme.repository.ClientProfileRepository;
import com.charter.interpretme.rest.entity.ClientProfile;
import com.charter.interpretme.rest.entity.ServiceRequest;
import com.charter.interpretme.rest.entity.VolunteerProfile;

@Component
public class VolunteerNotificationEmailSender {
    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private ConfirmationLinkGenerator confirmationLinkGenerator;
    @Autowired
    private VelocityEngine velocityEngine;
    @Autowired
    private ClientProfileRepository clientProfileRepository;

    @Async
    public void sendEmailToVolunteers(ServiceRequest serviceRequest, List<VolunteerProfile> volunteerProfiles) {
        ClientProfile clientProfile = clientProfileRepository.findOne(serviceRequest.getClientId());
        volunteerProfiles.parallelStream()
                .map(volunteerProfile -> createEmailNotificationInfo(serviceRequest, clientProfile, volunteerProfile))
                .map(this::sendNotificationEmail)
                .collect(Collectors.toList());
    }

    private VolunteerEmailNotificationInfo createEmailNotificationInfo(ServiceRequest serviceRequest,
            ClientProfile clientProfile,
            VolunteerProfile volunteerProfile) {
        String confirmationLink = confirmationLinkGenerator
                .generateLink(volunteerProfile.getId(), clientProfile.getId(), serviceRequest.getId());
        String clientName =
                valueMapper(clientProfile::getFirstName) + " " + valueMapper(clientProfile::getLastName);
        String volunteerName = valueMapper(volunteerProfile::getFirstName) + " " + valueMapper(
                volunteerProfile::getLastName);
        String volunteerEmail = volunteerProfile.getEmailAddress();
        String appointmentFrom = getFormattedDate(serviceRequest.getAppointmentFrom());
        String appointmentTo = getFormattedDate(serviceRequest.getAppointmentTo());
        String typeOfRequest = serviceRequest.getInPerson() ? "In Person" : "Over phone";

        return new VolunteerEmailNotificationInfo(volunteerName, clientName, confirmationLink, volunteerEmail,
                appointmentFrom, appointmentTo, typeOfRequest);
    }

    private <T> T valueMapper(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (NullPointerException e) {
            return null;
        }
    }

    private String getFormattedDate(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return date.format(formatter);
    }

    public boolean sendNotificationEmail(VolunteerEmailNotificationInfo emailConfirmationNotificationInfo) {

        emailSender.send(paramMimeMessage -> {
            String emailRecipients = emailConfirmationNotificationInfo.getVolunteerEmailAddress();
            MimeMessageHelper message = new MimeMessageHelper(paramMimeMessage, true, "UTF-8");
            message.setTo(emailRecipients);
            message.setSubject("Notification");
            message.setText(buildEmailContent(emailConfirmationNotificationInfo), true);
        });
        return true;
    }

    private String buildEmailContent(VolunteerEmailNotificationInfo emailConfirmationNotificationInfo) {
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("emailInfo", emailConfirmationNotificationInfo);
        StringWriter stringWriter = new StringWriter();
        velocityEngine.mergeTemplate("/templates/volunteerNotificationEmailTemplate.vm", "UTF-8", velocityContext,
                stringWriter);
        return stringWriter.toString();
    }

}
