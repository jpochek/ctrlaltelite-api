package com.charter.interpretme;

import java.net.URL;
import java.net.URLEncoder;

import org.springframework.stereotype.Component;

import com.charter.interpretme.utils.EncryptionUtil;

@Component
public class ConfirmationLinkGenerator {

    public String generateLink(URL url, String volunteerId, String clientId, String serviceRequestId) {
        try {
            String confirmationId = EncryptionUtil.encrypt(volunteerId + "-" + clientId + "-" + serviceRequestId);
            URL confirmationUrl = new URL(url.getProtocol(), url.getHost(), url.getPort(), "/api/confirm");
            return confirmationUrl.toString() + "/" + URLEncoder.encode(confirmationId, "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException("Error creating confirmation url.");
        }
    }
}
