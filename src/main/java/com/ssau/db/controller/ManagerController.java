package com.ssau.db.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssau.db.model.Manager;
import com.ssau.db.repo.ManagerRepository;
import com.ssau.db.repo.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;


@RestController
@RequestMapping("manager")
public class ManagerController {

    private final ModelRepository modelDao;
    private final ManagerRepository managerDao;

    @Autowired
    public ManagerController(ModelRepository modelDao, ManagerRepository managerDao) {
        this.modelDao = modelDao;
        this.managerDao = managerDao;
    }

    @GetMapping("/getManager")
    public List<Manager> getManager() throws JsonProcessingException {
        List<Manager> manageres = managerDao.getAllBy();
        return manageres;
    }

    @GetMapping("/getManagerById")
    public Manager getManagerById(@PathParam("managerId") String managerId) throws JsonProcessingException {
        Manager manager = managerDao.getByID(Integer.valueOf(managerId));
        return manager;
    }

    @PostMapping("/addNewModel")
    public void addNewManager(
            @PathParam("fullName") String fullName,
            @PathParam("costOfServices") String costOfServices,
            @PathParam("experience") String experience,
            @PathParam("phone") String phone,
            @PathParam("email") String email
    ) {
        Manager manager = new Manager();
        manager.setFullName(fullName);
        manager.setCostOfServices(Integer.valueOf(costOfServices));
        manager.setExperience(Integer.valueOf(experience));
        manager.setPhone(phone);
        manager.setEmail(email);
        managerDao.save(manager);
    }

    @PutMapping("/updateManager")
    public void updateManager(
            @PathParam("managerId") String managerId,
            @DefaultValue("") @PathParam("fullName") String fullName,
            @DefaultValue("") @PathParam("costOfServices") String costOfServices,
            @DefaultValue("") @PathParam("experience") String experience,
            @DefaultValue("") @PathParam("phone") String phone,
            @DefaultValue("") @PathParam("email") String email) {
        Manager manager = managerDao.getByID(Integer.valueOf(managerId));
        if (!fullName.isEmpty()) {
            manager.setFullName(fullName);
        }
        if (!costOfServices.isEmpty()) {
            manager.setCostOfServices(Integer.valueOf(costOfServices));
        }
        if (!experience.isEmpty()) {
            manager.setExperience(Integer.valueOf(experience));
        }
        if (!phone.isEmpty()) {
            manager.setPhone(phone);
        }
        if (!email.isEmpty()) {
            manager.setEmail(email);
        }
        managerDao.save(manager);
    }

    @DeleteMapping("/deleteManager")
    public void deleteManager(@PathParam("managerId") String managerId) {
        Manager manager = managerDao.getByID(Integer.valueOf(managerId));
        managerDao.delete(manager);
    }
}
