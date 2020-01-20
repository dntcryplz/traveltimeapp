package com.dumitru.timetravelapp.utils;

import com.dumitru.timetravelapp.constants.Constants;
import org.apache.logging.log4j.message.StringFormattedMessage;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public final class Utility {

    private Utility() {

    }

    public static Optional<Double> calculateDistanceBasedOnGeolocation(final String departureLocation, final String destinationLocation) {

        final String[] departureLatLong = departureLocation.split(Constants.LOCATION_DELIMITER);
        final double departureLatitude = Double.parseDouble(departureLatLong[0]);
        final double departureLongitude = Double.parseDouble(departureLatLong[1]);

        final String[] destinationLatLong = destinationLocation.split(Constants.LOCATION_DELIMITER);
        final double destinationLat = Double.parseDouble(destinationLatLong[0]);
        final double destinationLong = Double.parseDouble(destinationLatLong[1]);
        final double theta = departureLongitude - destinationLong;

        if ((departureLatitude == departureLongitude) && (departureLongitude == destinationLong)) {
            return Optional.empty();
        } else {
            double dist = Math.sin(Math.toRadians(departureLatitude)) * Math.sin(Math.toRadians(destinationLat))
                    + Math.cos(Math.toRadians(departureLatitude)) * Math.cos(Math.toRadians(destinationLat)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515 * 1.609344 * Constants.METERS_PER_KM;
            return Optional.of(dist); // Return direct line optional of distance in meters
        }
    }

    public static Long convertStringTimeToSeconds(final String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT);
        LocalTime localTime = LocalTime.parse(date, dateTimeFormatter);

        return Duration.ofHours(localTime.getHour()).getSeconds()
                + Duration.ofMinutes(localTime.getMinute()).getSeconds() + localTime.getSecond();
    }

    public static LocalTime convertStringToLocalTime(final String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT);
        return LocalTime.parse(date, dateTimeFormatter);
    }

    public static Long convertLocalTimeToSeconds(final LocalTime time) {
        return Duration.ofHours(time.getHour()).getSeconds()
                + Duration.ofMinutes(time.getMinute()).getSeconds() + time.getSecond();
    }

    public static Double convertToInternationalMetricBaseUnit(Double velocity) {
        return velocity / (Constants.SECONDS_PER_HOUR / Constants.METERS_PER_KM);
    }

    public static String prepareFormattedResponse(final long arrivalTime, final long departureTime) {
        long calculatedSeconds = arrivalTime - departureTime;
        Duration duration = Duration.ofSeconds(arrivalTime - departureTime);
        if (duration.toDays() > 0) {
            long remainingSeconds = calculatedSeconds - duration.toDays() * Constants.SECONDS_PER_DAY;
            return new StringFormattedMessage("Arrive time, after : %s Day(s) at: %s", duration.toDays(), LocalTime.ofSecondOfDay(remainingSeconds)).getFormattedMessage();
        }
        return new StringFormattedMessage("Arrive at: %s", LocalTime.ofSecondOfDay(arrivalTime)).getFormattedMessage();
    }
}
