package com.charter.interpretme;

import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Supplier;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.charter.interpretme.rest.entity.ClientProfile;
import com.charter.interpretme.rest.entity.ServiceRequest;
import com.charter.interpretme.rest.entity.VolunteerProfile;

@Component
public class ClientConfirmationEmailSender {

    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private VelocityEngine velocityEngine;

    public void sendEmailToClient(ServiceRequest serviceRequest, ClientProfile clientProfile,
            VolunteerProfile volunteerProfile) {

        ClientEmailConfirmationInfo clientEmailConfirmationInfo = createClientConfirmationEmailSender(serviceRequest,
                clientProfile, volunteerProfile);
        sendConfirmationEmail(clientEmailConfirmationInfo);

    }

    private ClientEmailConfirmationInfo createClientConfirmationEmailSender(ServiceRequest serviceRequest,
            ClientProfile clientProfile,
            VolunteerProfile volunteerProfile) {
        String clientName =
                valueMapper(clientProfile::getFirstName) + " " + valueMapper(clientProfile::getLastName);
        String volunteerName = valueMapper(volunteerProfile::getFirstName) + " " + valueMapper(
                volunteerProfile::getLastName);
        String clientEmail = clientProfile.getEmailAddress();
        String volunteerImage = volunteerProfile.getPhotoLocation();
        String appointmentFrom = getFormattedDate(serviceRequest.getAppointmentFrom());
        String appointmentTo = getFormattedDate(serviceRequest.getAppointmentTo());

        return new ClientEmailConfirmationInfo(clientName, clientEmail,volunteerName, volunteerImage, appointmentFrom,
                appointmentTo);
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

    public boolean sendConfirmationEmail(ClientEmailConfirmationInfo clientEmailConfirmationInfo) {

        emailSender.send(paramMimeMessage -> {
            String emailRecipients = clientEmailConfirmationInfo.getClientEmail();
            MimeMessageHelper message = new MimeMessageHelper(paramMimeMessage, true, "UTF-8");
            message.setTo(emailRecipients);
            message.setSubject("Confirmation");
            message.setText(buildEmailContent(clientEmailConfirmationInfo), true);
        });
        return true;
    }

    private String buildEmailContent(ClientEmailConfirmationInfo clientEmailConfirmationInfo) {
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("emailInfo", clientEmailConfirmationInfo);
        StringWriter stringWriter = new StringWriter();
        velocityEngine.mergeTemplate("/templates/clientConfirmationEmailTemplate.vm", "UTF-8", velocityContext,
                stringWriter);
        return stringWriter.toString();
    }

}
