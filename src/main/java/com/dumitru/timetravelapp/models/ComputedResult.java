package com.dumitru.timetravelapp.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Setter
@Getter
public class ComputedResult {

    public String formattedResponse;
    public List<Category> categories;
    public LocalTime arrivalTime;
}
