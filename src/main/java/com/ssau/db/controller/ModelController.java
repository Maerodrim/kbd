package com.ssau.db.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssau.db.model.Manager;
import com.ssau.db.model.Model;
import com.ssau.db.repo.ManagerRepository;
import com.ssau.db.repo.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;


@RestController
@RequestMapping("model")
public class ModelController {

    private final ModelRepository modelDao;
    private final ManagerRepository managerDao;

    @Autowired
    public ModelController(ModelRepository modelDao, ManagerRepository managerDao) {
        this.modelDao = modelDao;
        this.managerDao = managerDao;
    }

    @GetMapping("/getModel")
    public List<Model> getModel() throws JsonProcessingException {
        List<Model> models = modelDao.getAllBy();
        return models;
    }

    @GetMapping("/getModelById")
    public Model getModelById(@PathParam("modelId") String modelId) throws JsonProcessingException {
        Model model = modelDao.getByID(Integer.valueOf(modelId));
        return model;
    }

    @PostMapping("/addNewModel")
    public String addNewModel(
            @PathParam("fullName") String fullName,
            @PathParam("age") String age,
            @PathParam("brand") String brand,
            @PathParam("body") String body,
            @PathParam("contractNumber") String contractNumber,
            @PathParam("phone") String phone,
            @PathParam("managerId") String managerId
    ) {
        Model model = new Model();

        model.setFullName(fullName);
        model.setAge(Integer.parseInt(age));
        model.setBrand(brand);
        model.setBody(body);
        model.setContractNumber(Integer.parseInt(contractNumber));
        model.setPhone(phone);

        Manager manager = managerDao.getByID(Integer.valueOf(managerId));
        model.setManager(manager);

        modelDao.save(model);
        return "ok";
    }

    @PutMapping("/updateModel")
    public String updateModel(
            @PathParam("modelId") String modelId,
            @DefaultValue("") @PathParam("fullName") String fullName,
            @DefaultValue("") @PathParam("age") String age,
            @DefaultValue("") @PathParam("brand") String brand,
            @DefaultValue("") @PathParam("body") String body,
            @DefaultValue("") @PathParam("contractNumber") String contractNumber,
            @DefaultValue("") @PathParam("phone") String phone) {
        Model model = modelDao.getByID(Integer.valueOf(modelId));
        if (!fullName.isEmpty()) {
            model.setFullName(fullName);
        }
        if (!age.isEmpty()) {
            model.setAge(Integer.parseInt(age));
        }
        if (!brand.isEmpty()) {
            model.setBrand(brand);
        }
        if (!body.isEmpty()) {
            model.setBody(body);
        }
        if (!contractNumber.isEmpty()) {
            model.setContractNumber(Integer.parseInt(contractNumber));
        }
        if (!phone.isEmpty()) {
            model.setPhone(phone);
        }
        modelDao.save(model);
        return "ok";
    }

    @DeleteMapping("/deleteModel")
    public void deleteModel(@PathParam("modelId") String modelId) {
        Model model = modelDao.getByID(Integer.valueOf(modelId));
        modelDao.delete(model);
    }
}
