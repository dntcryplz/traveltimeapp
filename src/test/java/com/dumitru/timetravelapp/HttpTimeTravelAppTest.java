package com.dumitru.timetravelapp;

import com.dumitru.timetravelapp.models.ComputedResult;
import com.dumitru.timetravelapp.models.InputParams;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpTimeTravelAppTest {

    private static final String DEPARTURE_TIME = "07:00:00";
    private static final String DEPARTURE_LOCATION = "32.9697&-96.80322";
    private static final String DESTINATION_LOCATION = "29.46786&-98.53506";

    @LocalServerPort
    private int randomServerPort;

    @Test
    public void retrieveTimeTravelDetailsTest() {
        RestTemplate restTemplate = new RestTemplate();
        InputParams inputParams = new InputParams();
        inputParams.setDepartureTime(DEPARTURE_TIME);
        inputParams.setDepartureLocation(DEPARTURE_LOCATION);
        inputParams.setDestinationLocation(DESTINATION_LOCATION);


        ComputedResult computedResult = restTemplate.postForObject("http://localhost:" + randomServerPort + "/computeResults", inputParams, ComputedResult.class);

        assertThat("16:34:06").isEqualTo(computedResult.getArrivalTime().toString());

    }
}
