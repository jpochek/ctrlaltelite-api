package com.charter.interpretme;

import com.charter.interpretme.repository.VolunteerProfileRepository;
import com.charter.interpretme.rest.entity.VolunteerProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/profiles")
public class VolunteerProfileController {

    @Autowired
    private VolunteerProfileRepository volunteerProfileRepository;

    @GetMapping
    public List<VolunteerProfile> getVolunteerProfiles() {
        return volunteerProfileRepository.findAll();
    }
}
