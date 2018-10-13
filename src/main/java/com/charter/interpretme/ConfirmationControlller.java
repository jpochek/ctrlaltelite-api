package com.charter.interpretme;

import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/confirm")
public class ConfirmationControlller {
    @Autowired
    private ConfirmationLinkGenerator confirmationLinkGenerator;
    @Autowired
    private MainSender mainSender;

    @GetMapping("/{confirmationId}")
    public void confirm(@PathVariable String confirmationId) throws UnknownHostException, MalformedURLException {
        confirmationLinkGenerator.generateLink(confirmationId);
        Map<String, String> volunteerEmailById = new HashMap<String, String>(){{
            put("10001","naresh.mjan@gmail.com");
            put("10002","vsambaraju@gmail.com");
        }};
        mainSender.sendEmailToVolunteers(volunteerEmailById);
//        String volunteerId = EncryptionUtil.decrypt(confirmationId);

    }

}
