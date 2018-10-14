package com.charter.interpretme;

import java.net.InetAddress;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.charter.interpretme.utils.EncryptionUtil;

@Component
public class ConfirmationLinkGenerator {
    @Autowired
    private Environment environment;

    public String generateLink(String volunteerId, String clientId, String serviceRequestId) {
        try {
            String confirmationId = EncryptionUtil.encrypt(volunteerId + "-" + clientId + "-" + serviceRequestId);
            String port = environment.getProperty("server.port");
            String host = InetAddress.getLocalHost().getCanonicalHostName();
            URL url = new URL("http", host, Integer.parseInt(port), "");
            return url.toString() + "/api/confirm/" + URLEncoder.encode(confirmationId, "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException("Error creating confirmation url.");
        }
    }
}
