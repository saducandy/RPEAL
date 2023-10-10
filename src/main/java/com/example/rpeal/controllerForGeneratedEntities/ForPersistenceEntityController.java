package com.example.rpeal.controllerForGeneratedEntities;

import com.example.rpeal.RepoForGeneEntities.ForPersistenceEntityRepo;
import com.example.rpeal.genetatedEntities.ForPersistenceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/RPEAL/forPersistenceEntity")
public class ForPersistenceEntityController {

    private ForPersistenceEntityRepo forPersistenceEntityRepo;

    /*************************************************************/


    @Autowired
    public void setForPersistenceEntityRepo(ForPersistenceEntityRepo forPersistenceEntityRepo) {
        this.forPersistenceEntityRepo = forPersistenceEntityRepo;
    }

    /*************************************************************/



    @PostMapping("/save")
    public ForPersistenceEntity savePersistenceEntity(@RequestBody ForPersistenceEntity data){

        return forPersistenceEntityRepo.save(data);

    }


    @GetMapping("/findAll")
    public List<ForPersistenceEntity> getPersistenceEntity(){
        return forPersistenceEntityRepo.findAll();
    }


}
