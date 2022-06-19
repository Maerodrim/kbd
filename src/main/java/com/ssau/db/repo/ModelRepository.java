package com.ssau.db.repo;

import com.ssau.db.model.Model;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepository extends CrudRepository<Model, Integer> {
    List<Model> getAllBy();
    Model getByID(Integer id);
}
