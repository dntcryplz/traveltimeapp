package com.dumitru.timetravelapp;

import com.dumitru.timetravelapp.controller.TimeTravelController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TimeTravelApplicationTests {

    @Autowired
    private TimeTravelController timeTravelController;

    @Test
    void contextLoads() {
        assertThat(timeTravelController).isNotNull();
    }

}
