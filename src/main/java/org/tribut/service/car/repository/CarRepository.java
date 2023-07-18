package org.tribut.service.car.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.tribut.service.car.model.Car;

public interface CarRepository extends MongoRepository<Car, String> {
}
