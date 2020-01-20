package com.dumitru.timetravelapp.service;

import com.dumitru.timetravelapp.constants.Constants;
import com.dumitru.timetravelapp.models.Category;
import com.dumitru.timetravelapp.models.InputParams;
import com.dumitru.timetravelapp.utils.Utility;
import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Optional;

@Service
public class ComputationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ComputationService.class);

    private int departureIndex = Constants.START_CATEGORY_INDEX;

    public Pair<Long, Long> retrieveArrivalTime(InputParams inputParams) {

        final Long departureTime = Utility.convertStringTimeToSeconds(inputParams.getDepartureTime());

        final Optional<Double> totalDistance = Utility.calculateDistanceBasedOnGeolocation(inputParams.getDepartureLocation(), inputParams.getDestinationLocation());
        final Optional<Category> departureCategory = findDepartureDetails(Utility.convertStringToLocalTime(inputParams.getDepartureTime()));

        validateInputData(totalDistance, departureCategory);

        Double velocity = Utility.convertToInternationalMetricBaseUnit(departureCategory.get().getVelocity());
        Double endOfTimeSlice = Utility.convertLocalTimeToSeconds(departureCategory.get().getEndTime()).doubleValue();

        Double currentTime = departureTime.doubleValue();
        Double distance = totalDistance.get();
        Double arrivalTime = currentTime + (distance / velocity);

        while (arrivalTime > endOfTimeSlice) {

            distance = distance - velocity * (endOfTimeSlice - currentTime);

            velocity = Utility.convertToInternationalMetricBaseUnit(Constants.TIME_INTERVAL_FOR_TRAFFIC_DENSITY.get(departureIndex).getVelocity());

            currentTime = endOfTimeSlice;
            endOfTimeSlice = Utility.convertLocalTimeToSeconds(Constants.TIME_INTERVAL_FOR_TRAFFIC_DENSITY.get(departureIndex).getEndTime()).doubleValue();

            arrivalTime = currentTime + (distance / velocity);
            setAdjustedDepartureIndex();
        }
        return new Pair<>(arrivalTime.longValue(), departureTime);
    }

    private Optional<Category> findDepartureDetails(LocalTime localTime) {
        for (Category category : Constants.TIME_INTERVAL_FOR_TRAFFIC_DENSITY) {
            setAdjustedDepartureIndex();
            if (isInCurrentTimeSlice(localTime, category)) {
                return Optional.of(category);
            }
        }
        return Optional.empty();
    }

    private void validateInputData(final Optional<Double> totalDistance, final Optional<Category> departureCategory) {
        totalDistance.orElseThrow(() -> new RuntimeException("Invalid input data, the distance between is 0 between departure and the destination"));
        departureCategory.orElseThrow(() -> new RuntimeException("For the specified departure time, no category found"));
    }

    private boolean isInCurrentTimeSlice(final LocalTime localTime, final Category category) {
        return (localTime.equals(category.getStartTime()) || localTime.isAfter(category.getStartTime())) && localTime.isBefore(category.getEndTime());
    }

    private void setAdjustedDepartureIndex() {
        if (departureIndex == Constants.TIME_INTERVAL_FOR_TRAFFIC_DENSITY.size() - 1) {
            departureIndex = Constants.START_CATEGORY_INDEX;
        } else {
            departureIndex++;
        }
    }
}
