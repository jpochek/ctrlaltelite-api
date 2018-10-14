package com.charter.interpretme;

public class ClientEmailConfirmationInfo {
    private final String clientName;
    private final String clientEmail;
    private final String volunteerName;
    private final String volunteerImageUrl;
    private final String appointmentFrom;
    private final String appointmentTo;

    public ClientEmailConfirmationInfo(String clientName, String clientEmail, String volunteerName,
            String volunteerImageUrl,
            String appointmentFrom, String appointmentTo) {
        this.clientName = clientName;
        this.clientEmail = clientEmail;
        this.volunteerName = volunteerName;
        this.volunteerImageUrl = volunteerImageUrl;
        this.appointmentFrom = appointmentFrom;
        this.appointmentTo = appointmentTo;
    }

    public String getClientName() {
        return clientName;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public String getVolunteerName() {
        return volunteerName;
    }

    public String getVolunteerImageUrl() {
        return volunteerImageUrl;
    }

    public String getAppointmentFrom() {
        return appointmentFrom;
    }

    public String getAppointmentTo() {
        return appointmentTo;
    }
}
