package org.tribut.service.car.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.tribut.service.car.dto.CarRequest;
import org.tribut.service.car.dto.CarResponse;
import org.tribut.service.car.service.CarService;

import java.util.List;

@RestController
@RequestMapping("/api/car")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CarResponse> getAllCars() {
        return carService.getALlCars();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCar(@RequestBody CarRequest carDto) {
        carService.createCar(carDto);
    }


}
