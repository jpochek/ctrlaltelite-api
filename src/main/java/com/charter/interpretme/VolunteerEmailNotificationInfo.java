package com.charter.interpretme;

public class VolunteerEmailNotificationInfo {
    private final String volunteerName;
    private final String clientName;
    private final String confirmationLink;
    private final String volunteerEmailAddress;
    private final String appointmentFrom;
    private final String appointmentTo;
    private final String typeOfRequest;

    public VolunteerEmailNotificationInfo(String volunteerName, String clientName, String confirmationLink,
            String volunteerEmailAddress, String appointmentFrom, String appointmentTo, String typeOfRequest) {
        this.volunteerName = volunteerName;
        this.clientName = clientName;
        this.confirmationLink = confirmationLink;
        this.volunteerEmailAddress = volunteerEmailAddress;
        this.appointmentFrom = appointmentFrom;
        this.appointmentTo = appointmentTo;
        this.typeOfRequest = typeOfRequest;
    }

    public String getVolunteerName() {
        return volunteerName;
    }

    public String getClientName() {
        return clientName;
    }

    public String getConfirmationLink() {
        return confirmationLink;
    }

    public String getVolunteerEmailAddress() {
        return volunteerEmailAddress;
    }

    public String getAppointmentFrom() {
        return appointmentFrom;
    }

    public String getAppointmentTo() {
        return appointmentTo;
    }

    public String getTypeOfRequest() {
        return typeOfRequest;
    }
}
