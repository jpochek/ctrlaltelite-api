package com.charter.interpretme.rest.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.charter.interpretme.utils.LocalDateTimeDeserializer;
import com.charter.interpretme.utils.LocalDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "servicerequest")
public class ServiceRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(name = "clientId")
    private String clientId;
    @Column(name = "volunteerId")
    private String volunteerId;
    @Column(name = "languageTo")
    private String languageTo;
    @Column(name = "languageFrom")
    private String languageFrom;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority")
    private Priority priority;
    @Column(name = "zipCode")
    private String zipCode;
    @Column(name = "state")
    private String state;
    @Column(name = "streetAddress")
    private String streetAddress;
    @Column(name = "isInPerson")
    private Boolean isInPerson;
    @Column(name = "category")
    private String category;
    @Column(name = "description")
    private String description;
    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "appointmentDate")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime appointmentFrom = LocalDateTime.now();
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime appointmentTo = LocalDateTime.now();

    public ServiceRequest() {
    }

    public ServiceRequest(
            String id,
            String clientId,
            String volunteerId,
            String languageTo,
            String languageFrom,
            Priority priority,
            String zipCode,
            String state,
            String streetAddress,
            Boolean isInPerson,
            String category,
            String description,
            LocalDateTime appointmentFrom,
            LocalDateTime appointmentTo,
            Status status
    ) {
        this.id = id;
        this.clientId = clientId;
        this.volunteerId = volunteerId;
        this.languageTo = languageTo;
        this.languageFrom = languageFrom;
        this.priority = priority;
        this.zipCode = zipCode;
        this.state = state;
        this.streetAddress = streetAddress;
        this.isInPerson = isInPerson;
        this.category = category;
        this.description = description;
        this.status = status;
        //        this.appointmentFrom = appointmentFrom;
        //        this.appointmentTo = appointmentTo;
    }

    @JsonCreator
    public ServiceRequest(
            @JsonProperty("clientId") String clientId,
            @JsonProperty("volunteerId") String volunteerId,
            @JsonProperty("languageTo") String languageTo,
            @JsonProperty("languageFrom") String languageFrom,
            @JsonProperty("priority") Priority priority,
            @JsonProperty("zipCode") String zipCode,
            @JsonProperty("state") String state,
            @JsonProperty("streetAddress") String streetAddress,
            @JsonProperty("isInPerson") Boolean isInPerson,
            @JsonProperty("category") String category,
            @JsonProperty("description") String description,
            @JsonProperty("appointmentFrom") LocalDateTime appointmentFrom,
            @JsonProperty("appointmentTo") LocalDateTime appointmentTo,
            @JsonProperty("status") Status status

    ) {
        this.clientId = clientId;
        this.volunteerId = volunteerId;
        this.languageTo = languageTo;
        this.languageFrom = languageFrom;
        this.priority = priority;
        this.zipCode = zipCode;
        this.state = state;
        this.streetAddress = streetAddress;
        this.isInPerson = isInPerson;
        this.category = category;
        this.description = description;
        this.status = status;
        //        this.appointmentFrom = appointmentFrom;
        //        this.appointmentTo = appointmentTo;
    }

    public String getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public String getVolunteerId() {
        return volunteerId;
    }

    public String getClientId() {
        return clientId;
    }

    public String getLanguageTo() {
        return languageTo;
    }

    public String getLanguageFrom() {
        return languageFrom;
    }

    public Priority getPriority() {
        return priority;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getState() {
        return state;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public Boolean getInPerson() {
        return isInPerson;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }


    public LocalDateTime getAppointmentFrom() {
        return appointmentFrom;
    }

    public LocalDateTime getAppointmentTo() {
        return appointmentTo;
    }

    public static enum Priority {
        High, Mediaum, low
    }

    public static enum Status {
        Pending, Complete , Cancelled
    }

}
