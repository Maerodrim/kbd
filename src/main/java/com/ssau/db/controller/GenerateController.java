package com.ssau.db.controller;

import com.ssau.db.model.Car;
import com.ssau.db.model.CarDealer;
import com.ssau.db.model.Manager;
import com.ssau.db.model.Model;
import com.ssau.db.repo.CarDealerRepository;
import com.ssau.db.repo.CarRepository;
import com.ssau.db.repo.ManagerRepository;
import com.ssau.db.repo.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("generator")
public class GenerateController {

    private final ModelRepository modelRepository;
    private final ManagerRepository managerRepository;
    private final CarRepository carRepository;
    private final CarDealerRepository carDealerRepository;

    @Autowired
    public GenerateController(ModelRepository modelRepository,
                              ManagerRepository managerRepository,
                              CarRepository carRepository,
                              CarDealerRepository carDealerRepository) {
        this.modelRepository = modelRepository;
        this.managerRepository = managerRepository;
        this.carRepository = carRepository;
        this.carDealerRepository = carDealerRepository;
    }

    @PostMapping("generate")
    public void generate() {
        for (int i = 0; i < 10; i++) {
            createDealer();
            createManager();
        }
        List<Manager> managerList = managerRepository.getAllBy();
        for (int i = 0; i < 10; i++) {
            createModel(managerList.get(0).getID());
        }

        List<Model> modelList = modelRepository.getAllBy();
        for (int i = 0; i < 10; i++) {
            createCar(modelList.get(0).getID());
        }
    }

    public void createDealer() {
        CarDealer carDealer = new CarDealer();
        carDealer.setName(getRandomString());
        carDealer.setAddress(getRandomString());
        carDealer.setPhone(getRandomString());
        carDealerRepository.save(carDealer);
    }

    public void createCar(Integer model_id) {
        Car car = new Car();
        car.setCost(getRandomInteger());
        car.setColor(getRandomString());
        car.setNumberD(getRandomInteger());
        Model model = modelRepository.getByID(model_id);
        car.setModel(model);
        carRepository.save(car);
    }

    public void createModel(Integer managerId) {
        Model model = new Model();

        model.setFullName(getRandomString());
        model.setAge(getRandomInteger());
        model.setBrand(getRandomString());
        model.setBody(getRandomString());
        model.setContractNumber(getRandomInteger());
        model.setPhone(getRandomString());
        Manager manager = managerRepository.getByID(managerId);
        model.setManager(manager);
        modelRepository.save(model);
    }

    public void createManager() {
        Manager manager = new Manager();
        manager.setFullName(getRandomString());
        manager.setCostOfServices(getRandomInteger());
        manager.setExperience(getRandomInteger());
        manager.setPhone(getRandomString());
        manager.setEmail(getRandomString());
        managerRepository.save(manager);
    }

    String getRandomString() {
        return "test";
    }

    Integer getRandomInteger() {
        return 0;
    }
}

