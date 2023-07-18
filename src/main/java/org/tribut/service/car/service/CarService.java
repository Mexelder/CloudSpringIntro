package org.tribut.service.car.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.tribut.service.car.dto.CarRequest;
import org.tribut.service.car.dto.CarResponse;
import org.tribut.service.car.model.Car;
import org.tribut.service.car.repository.CarRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CarService {

    private final CarRepository carRepository;

    public List<CarResponse> getALlCars() {

        List<Car> cars = carRepository.findAll();
        return cars.stream().map(this::mapCarToCarResponse).collect(Collectors.toList());
    }

    private CarResponse mapCarToCarResponse(Car car) {
        return CarResponse.builder()
                .id(car.getId())
                .model(car.getModel())
                .color(car.getColor())
                .price(car.getPrice())
                .build();
    }

    public void createCar(CarRequest carDto) {
        Car car = mapCarRequestToCar(carDto);
        carRepository.save(car);
        log.info("Car {} is saved", car.getId());
    }

    private static Car mapCarRequestToCar(CarRequest carDto) {
        return Car.builder()
                .model(carDto.getModel())
                .color(carDto.getColor())
                .price(carDto.getPrice())
                .build();
    }
}
