package com.ssau.db.repo;

import com.ssau.db.model.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends CrudRepository<Car, Integer> {
    List<Car> getAllBy();
    Car getByID(Integer id);
}
