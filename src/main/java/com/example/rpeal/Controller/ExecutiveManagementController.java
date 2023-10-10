package com.example.rpeal.Controller;

import com.example.rpeal.Model.*;
import com.example.rpeal.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/RPEAL/EM/")
public class ExecutiveManagementController {

    /**Required Fields**/

    private ExecutiveManagementRepo executiveManagementRepo;
    private AffiliatedCompanyRepo affiliatedCompanyRepo;

    private EMHRepo emhRepo;
    private ACHRepo achRepo;
    private TSHHRepo tshhRepo;
    private ChHRepo chHRepo;
    private BMHRepo bmhRepo;
    private deletedEMRepo deletedEMRepo;



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

    @Autowired
    public void setDeletedEMRepo(com.example.rpeal.Repository.deletedEMRepo deletedEMRepo) {
        this.deletedEMRepo = deletedEMRepo;
    }

    @Autowired
    public void setEmhRepo(EMHRepo emhRepo) {
        this.emhRepo = emhRepo;
    }

    @Autowired
    public void setAchRepo(ACHRepo achRepo) {
        this.achRepo = achRepo;
    }

    @Autowired
    public void setTshhRepo(TSHHRepo tshhRepo) {
        this.tshhRepo = tshhRepo;
    }

    @Autowired
    public void setChHRepo(ChHRepo chHRepo) {
        this.chHRepo = chHRepo;
    }

    @Autowired
    public void setBmhRepo(BMHRepo bmhRepo) {
        this.bmhRepo = bmhRepo;
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

            executiveManagement.setCurrentPosition("EM");
            executiveManagement.setNumberOfAC(0);
            executiveManagement.setNumberOfChildren(0);
//            executiveManagement.setId(executiveManagement.getId());


        return executiveManagementRepo.save(executiveManagement);


    }

    @GetMapping("findAll")
    public List<ExecutiveManagement> getAllEM(){


        return executiveManagementRepo.findAll();
    }



    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteTSH(@PathVariable Long id){

        if (executiveManagementRepo.findById(id).isPresent()){

            ExecutiveManagement executiveManagement = executiveManagementRepo.findById(id).get();

            /******************Storing history before Update********************/
            EMHistory emHistory = new EMHistory();
            DeletedEM deletedEM = new DeletedEM();
            emHistory.setAction("DELETE");
            emHistory.setCurrentPosition(executiveManagement.getCurrentPosition());
            emHistory.setDate(ZonedDateTime.now(ZoneId.of("Africa/Addis_Ababa")));
            emHistory.setUserId(executiveManagement.getId());
            emHistory.setNameOfEmployee(executiveManagement.getNameOfEmployee());
            emHistory.setNameOfSpouse(executiveManagement.getNameOfSpouse());
            emHistory.setNumberOfAC(executiveManagement.getNumberOfAC());
            emHistory.setNumberOfChildren(executiveManagement.getNumberOfChildren());

            deletedEM.setAction("DELETE");
            deletedEM.setCurrentPosition(executiveManagement.getCurrentPosition());
            deletedEM.setDate(ZonedDateTime.now(ZoneId.of("Africa/Addis_Ababa")));
            deletedEM.setId(executiveManagement.getId());
            deletedEM.setNameOfEmployee(executiveManagement.getNameOfEmployee());
            deletedEM.setNameOfSpouse(executiveManagement.getNameOfSpouse());
            deletedEM.setNumberOfAC(executiveManagement.getNumberOfAC());
            deletedEM.setNumberOfChildren(executiveManagement.getNumberOfChildren());


            emhRepo.save(emHistory);
            deletedEMRepo.save(deletedEM);



            /********************************************************************/

            executiveManagementRepo.delete(executiveManagement);

            return ResponseEntity.status(HttpStatus.OK)
                    .body("{\"SuccessMessage\": \"SuccessFully Deleted "+ executiveManagement.getNameOfEmployee()+"\"}");

        }else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"ErrorMessage\": \"No Employee registered with the Given ID:(\"}");

        }

    }


    @PutMapping("update/{id}")
    public ResponseEntity<?> updateEM(@RequestBody ExecutiveManagement emToUpdate, @PathVariable Long id){



        if(executiveManagementRepo.findById(id).isPresent()){

            ExecutiveManagement em = executiveManagementRepo.findById(id).get();

            /******************Storing history before Update********************/
            EMHistory emHistory = new EMHistory();
            emHistory.setAction("UPDATE");
            emHistory.setCurrentPosition(em.getCurrentPosition());
            emHistory.setDate(ZonedDateTime.now(ZoneId.of("Africa/Addis_Ababa")));
            emHistory.setUserId(em.getId());
            emHistory.setNameOfEmployee(em.getNameOfEmployee());
            emHistory.setNameOfSpouse(em.getNameOfSpouse());
            emHistory.setNumberOfAC(em.getNumberOfAC());
            emHistory.setNumberOfChildren(em.getNumberOfChildren());

            emhRepo.save(emHistory);



            /********************************************************************/

            em.setCurrentPosition("EM");
            em.setNameOfSpouse(emToUpdate.getNameOfSpouse());
            em.setNameOfEmployee(emToUpdate.getNameOfEmployee());
//            em.setNumberOfAC(emToUpdate.getNumberOfAC());
//            em.setNumberOfChildren(emToUpdate.getNumberOfChildren());


            return ResponseEntity.status(HttpStatus.OK)
                    .body(executiveManagementRepo.save(em));

        }else {

            System.out.println("The user" + " " + emToUpdate.getNameOfEmployee() + " is not found. Please provide the correct user name.");
            return ResponseEntity.status(HttpStatus.OK)
                    .body("{\"errors\":[{\"actionMessage\": \"The user " + emToUpdate.getNameOfEmployee() + " is not found. Please provide the correct user name.\"}]}");

        }


    }


    @GetMapping("{id}")
    public ResponseEntity<?> getOneEM(@PathVariable Long id){

        if(executiveManagementRepo.findById(id).isPresent()){

            return ResponseEntity.status(HttpStatus.OK)
                    .body(executiveManagementRepo.findById(id).get());

        }else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"ErrorMessage\": \"No EM registered with the Given ID:(\"}");

        }

    }











    /************************/

}
