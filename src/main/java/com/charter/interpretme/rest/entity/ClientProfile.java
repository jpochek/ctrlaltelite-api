package com.charter.interpretme.rest.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

/**
 * Profile implementation for a client application user.
 */
@Entity
public class ClientProfile extends Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(length = 16)
    private ProfileType profileType = ProfileType.Client;

    // No-arg constructor...needed for Hibernate
    private ClientProfile() {
        super();
    }

    public ClientProfile(String username, String firstName, String lastName, List<String> languages,
                         String streetAddress1, String streetAddress2, String city, String state,
                         String postalCode, String emailAddress, String phoneNumber, Double averageRating) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.languages = languages;
        this.streetAddress1 = streetAddress1;
        this.streetAddress2 = streetAddress2;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.averageRating = averageRating;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof ClientProfile)) return false;

        ClientProfile that = (ClientProfile) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(firstName, that.firstName)
                .append(lastName, that.lastName)
                .append(profileType, that.profileType)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(firstName)
                .append(lastName)
                .append(profileType)
                .toHashCode();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ProfileType getProfileType() {
        return profileType;
    }

    public void setProfileType(ProfileType profileType) {
        this.profileType = profileType;
    }
}
