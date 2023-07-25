package org.tribut.service.car;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.tribut.service.car.dto.CarRequest;
import org.tribut.service.car.repository.CarRepository;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class CarServiceApplicationTest {

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private CarRepository carRepository;

    @Test
    void shouldCreateCar() throws Exception {
        CarRequest carRequest = getCarRequest();
        String carRequestString = objectMapper.writeValueAsString(carRequest);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/car")
                        .contentType(APPLICATION_JSON)
                        .content(carRequestString))
                .andExpect(status().isCreated());
        assertEquals(1, carRepository.findAll().size());
    }

    private CarRequest getCarRequest() {
        return CarRequest.builder().model("testCar").price(BigDecimal.TEN).color("red").build();
    }

}