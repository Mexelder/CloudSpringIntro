package org.tribut.service.car.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document("car")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Car {

    @Id
    private String id;
    private String model;
    private String color;
    private BigDecimal price;
}
