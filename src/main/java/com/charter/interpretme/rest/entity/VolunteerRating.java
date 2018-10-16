package com.charter.interpretme.rest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class VolunteerRating {
    @Id
    private String id;

    @Column(name = "averageRating")
    private  Double averageRating;

    public VolunteerRating() {
    }


    @JsonCreator
    public VolunteerRating(
            @JsonProperty("id") String id,
            @JsonProperty("averageRating") Double averageRating
    ) {
        this.id = id;
        this.averageRating = averageRating;
    }

    public String getId() {
        return id;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }
}
