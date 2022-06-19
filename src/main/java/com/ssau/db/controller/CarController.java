package com.ssau.db.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssau.db.model.Car;
import com.ssau.db.model.Model;
import com.ssau.db.repo.CarRepository;
import com.ssau.db.repo.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("car")
public class CarController {


    private CarRepository carDao;
    private ModelRepository modelDao;

    @Autowired
    public CarController(CarRepository carDao, ModelRepository modelDao) {
        this.carDao = carDao;
        this.modelDao = modelDao;
    }

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/getCar")
    public List<Car> getCar() throws JsonProcessingException {
        List<Car> cars = carDao.getAllBy();

        return cars;
    }

    @GetMapping("/getCarById")
    public Car getCarById(@PathParam("carId") String carId) throws JsonProcessingException {
        Car car = carDao.getByID(Integer.valueOf(carId));

        return car;
    }

    @PostMapping("/addNewCar")
    public Car addNewCar(
            @PathParam("modelId") String model_id,
            @PathParam("cost") String cost,
            @PathParam("numberD") String numberD,
            @PathParam("color") String color) {

        Car car = new Car();
        car.setCost(Integer.parseInt(cost));
        car.setColor(color);
        car.setNumberD(Integer.parseInt(numberD));
        Model model = modelDao.getByID(Integer.valueOf(model_id));
        car.setModel(model);
        carDao.save(car);
        return car;
    }

    @PutMapping("/updateCar")
    public void updateCar(
            @PathParam("carId") String carId,
            @DefaultValue("") @PathParam("cost") String cost,
            @DefaultValue("") @PathParam("numberD") String numberD,
            @DefaultValue("") @PathParam("color") String color) {

        Car car = carDao.getByID(Integer.valueOf(carId));
        if (!cost.isEmpty()) {
            car.setCost(Integer.parseInt(cost));
        }
        if (!numberD.isEmpty()) {
            car.setNumberD(Integer.parseInt(numberD));
        }
        if (!color.isEmpty()) {
            car.setColor(color);
        }
        carDao.save(car);
    }

    @DeleteMapping("/deleteCar")
    public void deleteCar(@PathParam("carId") String carId) {
        Car car = carDao.getByID(Integer.valueOf(carId));
        carDao.delete(car);
    }
}
