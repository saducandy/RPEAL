package com.example.rpeal.Controller;

import com.example.rpeal.Model.AffiliatedCompany;
import com.example.rpeal.Model.ExecutiveManagement;
import com.example.rpeal.Repository.AffiliatedCompanyRepo;
import com.example.rpeal.Repository.ExecutiveManagementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/RPEAL/EM/")
public class ExecutiveManagementController {

    /**Required Fields**/

    private ExecutiveManagementRepo executiveManagementRepo;
    private AffiliatedCompanyRepo affiliatedCompanyRepo;

    /*********************/


    @Autowired
    public void setAffiliatedCompanyRepo(AffiliatedCompanyRepo affiliatedCompanyRepo) {
        this.affiliatedCompanyRepo = affiliatedCompanyRepo;
    }

    /**Autowiring**/




    @Autowired
    public void setExecutiveManagementRepo(ExecutiveManagementRepo executiveManagementRepo) {
        this.executiveManagementRepo = executiveManagementRepo;
    }


    /*********************/


    /**Method Definitions**/


    @PostMapping("save")
    public ExecutiveManagement postEM(@RequestBody ExecutiveManagement executiveManagement){

//        if (affiliatedCompanyRepo.findByAffiliateNameAndAffiliatePosition(executiveManagement.getNameOfEmployee(), "EM") !=null){
//
//            int numberOfAC = affiliatedCompanyRepo.findByAffiliateNameAndAffiliatePosition(executiveManagement.getNameOfEmployee(), executiveManagement.getCurrentPosition()).size();
//
//            executiveManagement.setNumberOfAC(numberOfAC);
//
//        }else {

            executiveManagement.setNumberOfAC(0);
            executiveManagement.setNumberOfChildren(0);


        return executiveManagementRepo.save(executiveManagement);


    }

    @GetMapping("findAll")
    public List<ExecutiveManagement> getAllEM(){


        return executiveManagementRepo.findAll();
    }



    /************************/

}
