package com.example.rpeal.Controller;

import com.example.rpeal.Model.AffiliatedCompany;
import com.example.rpeal.Model.BoardMembers;
import com.example.rpeal.Repository.AffiliatedCompanyRepo;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/RPEAL/AC/")
public class AffiliatedCompanyController {


    /**Required fields**/

    private AffiliatedCompanyRepo affiliatedCompanyRepo;

    /**********************/

    /**Autowiring**/

    @Autowired
    public void setAffiliatedCompanyRepo(AffiliatedCompanyRepo affiliatedCompanyRepo) {
        this.affiliatedCompanyRepo = affiliatedCompanyRepo;
    }

    /*********************/


    /**Methods**/


    @PostMapping("save")
    public AffiliatedCompany postAC(@RequestBody AffiliatedCompany affiliatedCompany){

        return affiliatedCompanyRepo.save(affiliatedCompany);

    }

    @GetMapping("findAll")
    public List<AffiliatedCompany> getAllAC(){
        return affiliatedCompanyRepo.findAll();
    }

    @GetMapping("findCompany")
    public List<AffiliatedCompany> getCompany(@RequestBody ObjectNode objectNode){

        String name = objectNode.get("Affiliate_Name").asText();
        Long id = objectNode.get("Affiliate_Id").asLong();

        if (affiliatedCompanyRepo.findByAffiliateIdAndAffiliateName(id, name).isEmpty()){

            System.out.println("No Company registered with this Affiliate!!");
            return null;
        }else {
            return affiliatedCompanyRepo.findByAffiliateIdAndAffiliateName(id, name);
        }



    }





    /*********************************/




}
