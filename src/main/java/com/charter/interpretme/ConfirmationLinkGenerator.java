package com.charter.interpretme;

import java.net.InetAddress;
import java.net.URL;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.charter.interpretme.utils.EncryptionUtil;

@Component
public class ConfirmationLinkGenerator {
    @Autowired
    private Environment environment;

    public String generateLink(String volunteerId) {
        try {
            String encodedVolunteerId = EncryptionUtil.encrypt(volunteerId);
            String port = environment.getProperty("server.port");
            String host = InetAddress.getLocalHost().getCanonicalHostName();
            URL url = new URL("http", host, Integer.parseInt(port), "");
            return url.toString() + "/api/confirm/" + encodedVolunteerId;
        } catch (Exception e) {
            throw new RuntimeException("Error creating confirmation url.");
        }
    }
}
