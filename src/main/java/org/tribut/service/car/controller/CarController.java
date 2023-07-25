package org.tribut.service.car.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.tribut.service.car.dto.CarRequest;
import org.tribut.service.car.dto.CarResponse;
import org.tribut.service.car.service.CarService;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/car")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping
    @ResponseStatus(OK)
    public List<CarResponse> getAllCars() {
        return carService.getALlCars();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void createCar(@RequestBody CarRequest carDto) {
        carService.createCar(carDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(OK)
    public void deleteCar(@PathVariable String id){
        carService.delete(id);
    }


}
