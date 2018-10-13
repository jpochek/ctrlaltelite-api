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
 * Representation of a Volunteer user of the application.
 */
@Entity
public class VolunteerProfile extends Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(length = 16)
    private ProfileType profileType = ProfileType.Volunteer;

    // TODO availability
    // TODO requestTypes
    private String photoLocation;

    // Private no-arg constructor needed for Hibernate
    private VolunteerProfile() {
        super();
    }

    public VolunteerProfile(String username, String firstName, String lastName, List<String> languages,
                            String streetAddress1, String streetAddress2, String city, String state,
                            String postalCode, String emailAddress, String phoneNumber, Double averageRating,
                            String photoLocation) {
        super(username, firstName, lastName, languages, streetAddress1, streetAddress2, city, state, postalCode,
                emailAddress, phoneNumber, averageRating);
        this.photoLocation = photoLocation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        VolunteerProfile that = (VolunteerProfile) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(profileType, that.profileType)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
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

    public String getPhotoLocation() {
        return photoLocation;
    }

    public void setPhotoLocation(String photoLocation) {
        this.photoLocation = photoLocation;
    }
}
