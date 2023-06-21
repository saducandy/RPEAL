package com.example.rpeal.Controller;

import com.example.rpeal.Model.ExecutiveManagement;
import com.example.rpeal.Model.TopShareHolders;
import com.example.rpeal.Repository.TopShareHoldersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/RPEAL/TSH")
public class TopShareHoldersController {


    /**Required Fields**/

    private TopShareHoldersRepo topShareHoldersRepo;

    /************************/

    /**AutoWiring**/

    @Autowired
    public void setTopShareHoldersRepo(TopShareHoldersRepo topShareHoldersRepo) {
        this.topShareHoldersRepo = topShareHoldersRepo;
    }

    /*****************/


    /**Methods**/


    @PostMapping("save")
    public TopShareHolders postTSH(@RequestBody TopShareHolders topShareHolders){

        return topShareHoldersRepo.save(topShareHolders);

    }

    @GetMapping("findAll")
    public List<TopShareHolders> getAllTSH(){
        return topShareHoldersRepo.findAll();
    }


    /**********************************/



}
