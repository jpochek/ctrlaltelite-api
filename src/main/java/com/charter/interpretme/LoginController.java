package com.charter.interpretme;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.charter.interpretme.repository.ClientProfileRepository;
import com.charter.interpretme.repository.VolunteerProfileRepository;
import com.charter.interpretme.rest.entity.Profile;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private ClientProfileRepository clientProfileRepository;
    @Autowired
    private VolunteerProfileRepository volunteerProfileRepository;

    @PostMapping("/{email}")
    public Profile findByEmail(@PathVariable("email") String emailAddress) {
        Profile profile = clientProfileRepository.findByEmailAddressContainingIgnoreCase(emailAddress);
        if(Objects.isNull(profile)){
            profile = volunteerProfileRepository.findByEmailAddressContainingIgnoreCase(emailAddress);
        }
        return profile;
    }

}
