package com.dumitru.timetravelapp.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class InputParams implements Serializable {
    String departureLocation;
    String destinationLocation;
    String departureTime;
}
