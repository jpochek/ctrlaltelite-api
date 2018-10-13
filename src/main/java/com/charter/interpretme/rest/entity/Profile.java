package com.charter.interpretme.rest.entity;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract entity of a application user's profile.
 */
public abstract class Profile {
    protected String username;
    protected String firstName;
    protected String lastName;
    protected List<String> languages;
    protected String streetAddress1;
    protected String streetAddress2;
    protected String city;
    protected String state;
    protected String postalCode;
    protected String emailAddress;
    protected String phoneNumber;
    protected Double averageRating;

    protected Profile() {
        username = StringUtils.EMPTY;
        firstName = StringUtils.EMPTY;
        lastName = StringUtils.EMPTY;
        languages = new ArrayList<>();
        streetAddress1 = StringUtils.EMPTY;
        streetAddress2 = StringUtils.EMPTY;
        city = StringUtils.EMPTY;
        state = StringUtils.EMPTY;
        postalCode = StringUtils.EMPTY;
        emailAddress = StringUtils.EMPTY;
        phoneNumber = StringUtils.EMPTY;
        averageRating = 0.0;
    }

    public Profile(String username, String firstName, String lastName, List<String> languages,
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

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
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
}
