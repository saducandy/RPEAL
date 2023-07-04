package com.example.rpeal.Controller;

import com.example.rpeal.Model.ExecutiveManagement;
import com.example.rpeal.Model.TopShareHolders;
import com.example.rpeal.Repository.AffiliatedCompanyRepo;
import com.example.rpeal.Repository.TopShareHoldersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/RPEAL/TSH")
public class TopShareHoldersController {


    /**Required Fields**/

    private TopShareHoldersRepo topShareHoldersRepo;
    private AffiliatedCompanyRepo affiliatedCompanyRepo;

    /************************/



    /**AutoWiring**/

    @Autowired
    public void setAffiliatedCompanyRepo(AffiliatedCompanyRepo affiliatedCompanyRepo) {
        this.affiliatedCompanyRepo = affiliatedCompanyRepo;
    }

    @Autowired
    public void setTopShareHoldersRepo(TopShareHoldersRepo topShareHoldersRepo) {
        this.topShareHoldersRepo = topShareHoldersRepo;
    }

    /*****************/


    /**Methods**/


    @PostMapping("save")
    public TopShareHolders postTSH(@RequestBody TopShareHolders topShareHolders){



//        if (affiliatedCompanyRepo.findByAffiliateNameAndAffiliatePosition(topShareHolders.getNameOfShareHolder(), "TSH") !=null){
//
//            int numberOfAC = affiliatedCompanyRepo.findByAffiliateNameAndAffiliatePosition(topShareHolders.getNameOfShareHolder(), "TSH").size();
//
//            topShareHolders.setNumberOfAC(numberOfAC);
//
//        }else {
//
//            topShareHolders.setNumberOfAC(0);
//
//        }
        topShareHolders.setNumberOfAC(0);
        topShareHolders.setNumberOfChildren(0);


        return topShareHoldersRepo.save(topShareHolders);



    }

    @GetMapping("findAll")
    public List<TopShareHolders> getAllTSH(){
        return topShareHoldersRepo.findAll();
    }


    /**********************************/



}
