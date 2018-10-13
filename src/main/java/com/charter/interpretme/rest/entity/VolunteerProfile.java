package com.charter.interpretme.rest.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Representation of a Volunteer user of the application.
 */
@Entity
public class VolunteerProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(length = 16)
    private ProfileType profileType = ProfileType.Volunteer;

    private String username;
    private String firstName;
    private String lastName;
    private String languages;
    private String streetAddress1;
    private String streetAddress2;
    private String city;
    private String state;
    private String postalCode;
    private String emailAddress;
    private String phoneNumber;
    private Double averageRating;
    private Integer age;
    private String gender;
    @Type(type = "yes_no")
    private Boolean meetInPerson;
    private String contactMethod;

    // TODO availability
    // TODO requestTypes
    private String photoLocation;

    // Private no-arg constructor needed for Hibernate
    private VolunteerProfile() {
    }

    public VolunteerProfile(String id, String username, String firstName, String lastName, String languages,
                            String streetAddress1, String streetAddress2, String city, String state,
                            String postalCode, String emailAddress, String phoneNumber, Double averageRating,
                            String photoLocation, Integer age, String gender, Boolean meetInPerson,
                            String contactMethod) {
        this.id = id;
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
        this.photoLocation = photoLocation;
        this.age = age;
        this.gender = gender;
        this.meetInPerson = meetInPerson;
        this.contactMethod = contactMethod;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public String getStreetAddress1() {
        return streetAddress1;
    }

    public void setStreetAddress1(String streetAddress1) {
        this.streetAddress1 = streetAddress1;
    }

    public String getStreetAddress2() {
        return streetAddress2;
    }

    public void setStreetAddress2(String streetAddress2) {
        this.streetAddress2 = streetAddress2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Boolean getMeetInPerson() {
        return meetInPerson;
    }

    public void setMeetInPerson(Boolean meetInPerson) {
        this.meetInPerson = meetInPerson;
    }

    public String getContactMethod() {
        return contactMethod;
    }

    public void setContactMethod(String contactMethod) {
        this.contactMethod = contactMethod;
    }
}
