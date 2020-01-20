package com.dumitru.timetravelapp;


import com.dumitru.timetravelapp.models.InputParams;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TimeTravelAppMockTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Mock test")
    public void shouldReturnDefaultMessage() throws Exception {
        InputParams inputParams = new InputParams();
        inputParams.setDepartureTime("07:00:00");
        inputParams.setDepartureLocation("32.9697&-96.80322");
        inputParams.setDestinationLocation("29.46786&-98.53506");

        String requestJson = TestUtils.serializeObject(inputParams);

        this.mockMvc.perform(post("/computeResults").contentType(MediaType.APPLICATION_JSON).content(requestJson)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("16:34:06")));
    }
}
