package com.ssau.db.repo;

import com.ssau.db.model.CarDealer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarDealerRepository extends CrudRepository<CarDealer, Integer> {
    List<CarDealer> getAllBy();
    CarDealer getByID(Integer id);
}
