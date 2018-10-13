package com.charter.interpretme;

import java.io.StringWriter;
import java.util.Map;
import java.util.stream.Collectors;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

@Component
public class MainSender {
    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private ConfirmationLinkGenerator confirmationLinkGenerator;
    @Autowired
    private VelocityEngine velocityEngine;

    public void sendEmailToVolunteers(Map<String, String> volunteerEmailById) {
        volunteerEmailById.entrySet().parallelStream()
                .map(entry -> sendNotificationEmail(entry.getValue(),
                        confirmationLinkGenerator.generateLink(entry.getKey())))
                .collect(Collectors.toList());
    }

    public boolean sendNotificationEmail(String emailId, String confirmationLink) {

        emailSender.send(new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage paramMimeMessage) throws Exception {
                String emailRecipients = emailId;
                MimeMessageHelper message = new MimeMessageHelper(paramMimeMessage, true, "UTF-8");
                message.setFrom("linge@hackathon.com");
                message.setTo(emailRecipients);
                message.setSubject("test subject");
                message.setText(buildEmailContent(confirmationLink), true);
            }
        });
        return true;
    }

    private String buildEmailContent(String confirmationLink) {
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("confirmationLink", confirmationLink);
        StringWriter stringWriter = new StringWriter();
        velocityEngine.mergeTemplate("/templates/confirmationEmailTemplate.vm", "UTF-8", velocityContext, stringWriter);
        return stringWriter.toString();
    }

}
