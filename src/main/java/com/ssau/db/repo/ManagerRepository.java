package com.ssau.db.repo;

import com.ssau.db.model.Manager;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagerRepository extends CrudRepository<Manager, Integer> {
    Manager getByID(Integer id);
    List<Manager> getAllBy();
}
