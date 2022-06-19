package com.ssau.db.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssau.db.model.CarDealer;
import com.ssau.db.repo.CarDealerRepository;
import com.ssau.db.repo.ManagerRepository;
import com.ssau.db.repo.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("carDealer")
public class CarDealerController {
    private final CarDealerRepository carDealerDao;
    private final ModelRepository modelDao;
    private final ManagerRepository managerDao;

    @Autowired
    public CarDealerController(CarDealerRepository carDealerDao,
                               ModelRepository modelDao,
                               ManagerRepository managerDao) {
        this.carDealerDao = carDealerDao;
        this.modelDao = modelDao;
        this.managerDao = managerDao;
    }

    @GetMapping("/getCarDealer")
    public List<CarDealer> getCarDealer() throws JsonProcessingException {
        List<CarDealer> carDealers = carDealerDao.getAllBy();

        return carDealers;
    }

    @GetMapping("/getCarDealerById")
    public CarDealer getCarDealerById(@PathParam("carDealerId") String carDealerId) throws JsonProcessingException {
        CarDealer carDealer = carDealerDao.getByID(Integer.valueOf(carDealerId));
        return carDealer;
    }

    @PostMapping("/addNewCarDealerId")
    public void addNewCarDealerId(
            @PathParam("name") String name,
            @PathParam("address") String address,
            @PathParam("phone") String phone
    ) {
        CarDealer carDealer = new CarDealer();
        carDealer.setName(name);
        carDealer.setAddress(address);
        carDealer.setPhone(phone);
        carDealerDao.save(carDealer);
    }

    @PutMapping("/updateCarDealer")
    public void updateCarDealer(
            @PathParam("carDealerId") String carDealerId,
            @DefaultValue("") @PathParam("name") String name,
            @DefaultValue("") @PathParam("address") String address,
            @DefaultValue("") @PathParam("phone") String phone) {
        CarDealer carDealer = carDealerDao.getByID(Integer.valueOf(carDealerId));
        if (!name.isEmpty()) {
            carDealer.setName(name);
        }
        if (!address.isEmpty()) {
            carDealer.setAddress(address);
        }
        if (!phone.isEmpty()) {
            carDealer.setPhone(phone);
        }
        carDealerDao.save(carDealer);
    }

    @DeleteMapping("/deleteCarDealer")
    public void deleteCarDealer(@PathParam("carDealerId") String carDealerId) {
        CarDealer carDealer = carDealerDao.getByID(Integer.valueOf(carDealerId));
        carDealerDao.delete(carDealer);
    }
}
