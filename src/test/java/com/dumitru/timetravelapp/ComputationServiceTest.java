package com.dumitru.timetravelapp;

import com.dumitru.timetravelapp.controller.TimeTravelController;
import com.dumitru.timetravelapp.models.InputParams;
import com.dumitru.timetravelapp.service.ComputationService;
import javafx.util.Pair;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class ComputationServiceTest {

    private static final String DEPARTURE_TIME = "07:00:00";
    private static final String DEPARTURE_LOCATION = "32.9697&-96.80322";
    private static final String DESTINATION_LOCATION = "29.46786&-98.53506";

    @InjectMocks
    private TimeTravelController timeTravelController;

    @Mock
    private ComputationService service;

    @Test
    public void computationServiceTest() throws Exception {
        LocalTime expectedLocalTime = LocalTime.of(01,00,50);

        InputParams inputParams = new InputParams();
        inputParams.setDepartureTime(DEPARTURE_TIME);
        inputParams.setDepartureLocation(DEPARTURE_LOCATION);
        inputParams.setDestinationLocation(DESTINATION_LOCATION);

        when(service.retrieveArrivalTime(inputParams)).thenReturn(new Pair<>(3650L, 2200L));

        LocalTime actualLocalTime = timeTravelController.retrieveTimeTravelDetails(inputParams).getArrivalTime();

        assertThat(actualLocalTime).isEqualTo(expectedLocalTime);
    }
}
