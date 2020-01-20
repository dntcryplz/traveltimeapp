package com.dumitru.timetravelapp.constants;

import com.dumitru.timetravelapp.models.Category;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public final class Constants {
    private Constants() {

    }

    public static final String DATE_FORMAT = "HH:mm:ss";
    public static final String LOCATION_DELIMITER = "&";
    public static final Double SECONDS_PER_HOUR = 3600.0;
    public static final Long SECONDS_PER_DAY = 86_400L;
    public static final Integer START_CATEGORY_INDEX = 0;
    public static final Double METERS_PER_KM = 1000.0;

    public static final List<Category> TIME_INTERVAL_FOR_TRAFFIC_DENSITY = new ArrayList<Category>() {{
        add(new Category().withStartTime(LocalTime.of(00, 00, 00)).withEndTime(LocalTime.of(6, 00, 00)).withVelocity(100.0).withVelocityMeasure("km/h"));
        add(new Category().withStartTime(LocalTime.of(6, 00, 00)).withEndTime(LocalTime.of(7, 00, 00)).withVelocity(90.0).withVelocityMeasure("km/h"));
        add(new Category().withStartTime(LocalTime.of(7, 00, 00)).withEndTime(LocalTime.of(8, 00, 00)).withVelocity(40.0).withVelocityMeasure("km/h"));
        add(new Category().withStartTime(LocalTime.of(8, 30, 00)).withEndTime(LocalTime.of(9, 00, 00)).withVelocity(35.0).withVelocityMeasure("km/h"));
        add(new Category().withStartTime(LocalTime.of(9, 30, 00)).withEndTime(LocalTime.of(10, 00, 00)).withVelocity(37.0).withVelocityMeasure("km/h"));
        add(new Category().withStartTime(LocalTime.of(10, 00, 00)).withEndTime(LocalTime.of(12, 00, 00)).withVelocity(40.0).withVelocityMeasure("km/h"));
        add(new Category().withStartTime(LocalTime.of(12, 00, 00)).withEndTime(LocalTime.of(14, 00, 00)).withVelocity(55.0).withVelocityMeasure("km/h"));
        add(new Category().withStartTime(LocalTime.of(14, 00, 00)).withEndTime(LocalTime.of(15, 00, 00)).withVelocity(53.0).withVelocityMeasure("km/h"));
        add(new Category().withStartTime(LocalTime.of(15, 00, 00)).withEndTime(LocalTime.of(16, 00, 00)).withVelocity(45.0).withVelocityMeasure("km/h"));
        add(new Category().withStartTime(LocalTime.of(16, 00, 00)).withEndTime(LocalTime.of(18, 00, 00)).withVelocity(40.0).withVelocityMeasure("km/h"));
        add(new Category().withStartTime(LocalTime.of(18, 00, 00)).withEndTime(LocalTime.of(18, 30, 00)).withVelocity(38.0).withVelocityMeasure("km/h"));
        add(new Category().withStartTime(LocalTime.of(18, 30, 00)).withEndTime(LocalTime.of(19, 00, 00)).withVelocity(35.0).withVelocityMeasure("km/h"));
        add(new Category().withStartTime(LocalTime.of(19, 00, 00)).withEndTime(LocalTime.of(20, 00, 00)).withVelocity(40.0).withVelocityMeasure("km/h"));
        add(new Category().withStartTime(LocalTime.of(20, 00, 00)).withEndTime(LocalTime.of(21, 00, 00)).withVelocity(60.0).withVelocityMeasure("km/h"));
        add(new Category().withStartTime(LocalTime.of(21, 00, 00)).withEndTime(LocalTime.of(22, 00, 00)).withVelocity(70.0).withVelocityMeasure("km/h"));
        add(new Category().withStartTime(LocalTime.of(22, 00, 00)).withEndTime(LocalTime.of(23, 59, 59)).withVelocity(95.0).withVelocityMeasure("km/h"));

    }};
}
