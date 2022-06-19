package com.ssau.db.controller;

import com.ssau.db.model.Car;
import com.ssau.db.model.CarDealer;
import com.ssau.db.model.Manager;
import com.ssau.db.model.Model;
import com.ssau.db.repo.CarDealerRepository;
import com.ssau.db.repo.CarRepository;
import com.ssau.db.repo.ManagerRepository;
import com.ssau.db.repo.ModelRepository;
import com.ssau.db.utils.Utils;
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
    int managerSize = 10;
    int modelSize = 10;
    int carSize = 100;

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

        for (int i = 0; i < managerSize; i++) {
            createDealer();
            createManager();
        }

        List<Manager> managerList = managerRepository.getAllBy();
        for (int i = 0; i < modelSize; i++) {
            createModel(managerList.get(Utils.rnd(managerSize)).getID());
        }

        List<Model> modelList = modelRepository.getAllBy();
        for (int i = 0; i < carSize; i++) {
            createCar(modelList.get(Utils.rnd(modelSize)).getID());
        }
    }

    public void createDealer() {
        CarDealer carDealer = new CarDealer();
        carDealer.setName(Utils.randomIdentifier());
        carDealer.setAddress(Utils.getRandomString());
        carDealer.setPhone(Utils.rnd(1000000000).toString());
        carDealerRepository.save(carDealer);
    }

    public void createCar(Integer model_id) {
        Car car = new Car();
        car.setCost(Utils.rnd(1000000000));
        car.setColor(Utils.getRandomString());
        car.setNumberD(Utils.getRandomInteger());
        Model model = modelRepository.getByID(model_id);
        car.setModel(model);
        carRepository.save(car);
    }

    public void createModel(Integer managerId) {
        Model model = new Model();

        model.setFullName(Utils.randomIdentifier());
        model.setAge(Utils.rnd(2022));
        model.setBrand(Utils.getBrand());
        model.setBody(Utils.getBody());
        model.setContractNumber(Utils.rnd(10000));
        model.setPhone(Utils.rnd(1000000000).toString());
        Manager manager = managerRepository.getByID(managerId);
        model.setManager(manager);
        modelRepository.save(model);
    }

    public void createManager() {
        Manager manager = new Manager();
        manager.setFullName(Utils.getFullName());
        manager.setCostOfServices(Utils.rnd(1000000000));
        manager.setExperience(Utils.rnd(30));
        manager.setPhone(Utils.rnd(1000000000).toString());
        manager.setEmail(Utils.getRandomString());
        managerRepository.save(manager);
    }
}

