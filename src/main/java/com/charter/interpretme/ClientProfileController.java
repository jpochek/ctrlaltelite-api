package com.charter.interpretme;

import com.charter.interpretme.repository.ClientProfileRepository;
import com.charter.interpretme.rest.entity.ClientProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller for interacting with client profiles.
 */
@RestController
@RequestMapping("/api/clients")
public class ClientProfileController {

    private ClientProfileRepository clientProfileRepository;

    @Autowired
    public ClientProfileController(ClientProfileRepository clientProfileRepository) {
        this.clientProfileRepository = clientProfileRepository;
    }

    @GetMapping
    public List<ClientProfile> getAllClients() {
        return clientProfileRepository.findAll();
    }

    @GetMapping("/{profileId}")
    public ClientProfile getClientById(@PathVariable("profileId") String profileId) {
        return clientProfileRepository.findOne(profileId);
    }

    @PostMapping("/user/{username}")
    public ClientProfile findByUsername(@PathVariable String username) {
        return clientProfileRepository.findByUsernameContainingIgnoreCase(username);
    }

    @PostMapping("/email/{email}")
    public ClientProfile findByEmail(@PathVariable("email") String emailAddress) {
        return clientProfileRepository.findByEmailAddressContainingIgnoreCase(emailAddress);
    }

    @PostMapping
    public ClientProfile addClientProfile(@RequestBody ClientProfile profile) {
        return clientProfileRepository.save(profile);
    }

    @PutMapping("/{profileId}")
    public ClientProfile editClientProfile(@PathVariable("profileId") String profileId,
                                           @RequestBody ClientProfile profile) {
        return clientProfileRepository.save(new ClientProfile(profileId, profile.getUsername(), profile.getFirstName(),
                profile.getLastName(), profile.getLanguages(), profile.getStreetAddress1(), profile.getStreetAddress2(),
                profile.getCity(), profile.getState(), profile.getPostalCode(), profile.getEmailAddress(),
                profile.getPhoneNumber(), profile.getAverageRating(), profile.getPhotoLocation(),
                profile.getAge(), profile.getGender(), profile.getMeetInPerson(), profile.getContactMethod()));
    }

    @DeleteMapping("/{profileId}")
    public void deleteProfile(@PathVariable("profileId") String profileId) {
        clientProfileRepository.delete(profileId);
    }
}
