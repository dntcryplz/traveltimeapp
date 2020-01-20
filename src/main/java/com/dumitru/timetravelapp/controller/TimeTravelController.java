package com.dumitru.timetravelapp.controller;

import com.dumitru.timetravelapp.constants.Constants;
import com.dumitru.timetravelapp.models.ComputedResult;
import com.dumitru.timetravelapp.models.InputParams;
import com.dumitru.timetravelapp.service.ComputationService;
import com.dumitru.timetravelapp.utils.Utility;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

@RestController
public class TimeTravelController {

    @Autowired
    private ComputationService computationService;

    @PostMapping(value = "/computeResults")
    @ResponseBody
    public ComputedResult retrieveTimeTravelDetails(@RequestBody InputParams inputParams) {

        Pair<Long /*arriveTime*/, Long /*departureTime*/> result = computationService.retrieveArrivalTime(inputParams);
        ComputedResult computedResult = new ComputedResult();
        computedResult.setFormattedResponse(Utility.prepareFormattedResponse(result.getKey(), result.getValue()));

        // At the moment this setArrival time will not fail if result seconds are < than 23:59:59 in seconds.
        computedResult.setArrivalTime(LocalTime.ofSecondOfDay(result.getKey()));
        computedResult.setCategories(Constants.TIME_INTERVAL_FOR_TRAFFIC_DENSITY);

        return computedResult;
    }
}
