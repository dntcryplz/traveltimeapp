package com.dumitru.timetravelapp.models;

import lombok.Getter;

import java.time.LocalTime;

@Getter
public class Category {

    private Double velocity;
    private String velocityMeasure;
    private LocalTime startTime;
    private LocalTime endTime;

    public Category withVelocity(double velocity) {
        this.velocity = velocity;
        return this;
    }

    public Category withStartTime(LocalTime startTime) {
        this.startTime = startTime;
        return this;
    }

    public Category withEndTime(LocalTime endTime) {
        this.endTime = endTime;
        return this;
    }

    public Category withVelocityMeasure(String velocityMeasure) {
        this.velocityMeasure = velocityMeasure;
        return this;
    }
}
