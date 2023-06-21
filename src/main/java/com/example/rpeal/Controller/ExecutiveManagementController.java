package com.example.rpeal.Controller;

import com.example.rpeal.Model.ExecutiveManagement;
import com.example.rpeal.Repository.ExecutiveManagementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/RPEAL/EM/")
public class ExecutiveManagementController {

    /**Required Fields**/

    private ExecutiveManagementRepo executiveManagementRepo;

    /*********************/


    /**Autowiring**/


    @Autowired
    public void setExecutiveManagementRepo(ExecutiveManagementRepo executiveManagementRepo) {
        this.executiveManagementRepo = executiveManagementRepo;
    }


    /*********************/


    /**Method Definitions**/


    @PostMapping("save")
    public ExecutiveManagement postEM(@RequestBody ExecutiveManagement executiveManagement){

        return executiveManagementRepo.save(executiveManagement);

    }

    @GetMapping("findAll")
    public List<ExecutiveManagement> getAllEM(){
        return executiveManagementRepo.findAll();
    }



    /************************/

}
