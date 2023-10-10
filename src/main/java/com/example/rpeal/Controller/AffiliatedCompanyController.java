package com.example.rpeal.Controller;

import com.example.rpeal.Model.ACHistory;
import com.example.rpeal.Model.AffiliatedCompany;
import com.example.rpeal.Model.ExecutiveManagement;
import com.example.rpeal.Model.TopShareHolders;
import com.example.rpeal.Repository.*;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/RPEAL/AC/")
public class AffiliatedCompanyController {


    /**Required fields**/

    private AffiliatedCompanyRepo affiliatedCompanyRepo;
    private ExecutiveManagementRepo executiveManagementRepo;
    private TopShareHoldersRepo topShareHoldersRepo;
    private ChildrenRepo childrenRepo;
    private EMHRepo emhRepo;
    private ACHRepo achRepo;
    private TSHHRepo tshhRepo;
    private ChHRepo chHRepo;
    private BMHRepo bmhRepo;

    /**********************/



    /**Autowiring**/

    @Autowired
    public void setChildrenRepo(ChildrenRepo childrenRepo) {
        this.childrenRepo = childrenRepo;
    }



    @Autowired
    public void setExecutiveManagementRepo(ExecutiveManagementRepo executiveManagementRepo) {
        this.executiveManagementRepo = executiveManagementRepo;
    }

    @Autowired
    public void setTopShareHoldersRepo(TopShareHoldersRepo topShareHoldersRepo) {
        this.topShareHoldersRepo = topShareHoldersRepo;
    }



    @Autowired
    public void setAffiliatedCompanyRepo(AffiliatedCompanyRepo affiliatedCompanyRepo) {
        this.affiliatedCompanyRepo = affiliatedCompanyRepo;
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


    /**Methods**/


    @PostMapping("save")
    public AffiliatedCompany postAC(@RequestBody AffiliatedCompany affiliatedCompany){


        affiliatedCompanyRepo.save(affiliatedCompany);

        int sizeOfCompanies = affiliatedCompanyRepo.findByAffiliateIdAndAffiliatePosition(affiliatedCompany.getAffiliateId(), affiliatedCompany.getAffiliatePosition()).size();
        System.out.println(sizeOfCompanies);

        if (affiliatedCompany.getAffiliatePosition().equals("EM")){

            if (executiveManagementRepo.findById(affiliatedCompany.getAffiliateId()).isPresent()) {

               ExecutiveManagement em1 = executiveManagementRepo.findById(affiliatedCompany.getAffiliateId()).get();
               em1.setNumberOfAC(sizeOfCompanies);
               executiveManagementRepo.save(em1);

            }else {

                System.out.println("There is no EM found!");
                return null;

            }

        } else if (affiliatedCompany.getAffiliatePosition().equals("TSH")) {

            if (topShareHoldersRepo.findById(affiliatedCompany.getAffiliateId()).isPresent()){

                TopShareHolders TSH1 = topShareHoldersRepo.findById(affiliatedCompany.getAffiliateId()).get();
                TSH1.setNumberOfAC(sizeOfCompanies);
                topShareHoldersRepo.save(TSH1);

            }else {

                System.out.println("There is no TSH found!");
                return null;

            }

        }

        return affiliatedCompanyRepo.save(affiliatedCompany);

    }

    @GetMapping("findAll")
    public List<AffiliatedCompany> getAllAC(){
        return affiliatedCompanyRepo.findAll();
    }


    @GetMapping("{id}")
    public ResponseEntity<?> getOneAC(@PathVariable Long id){

        if(affiliatedCompanyRepo.findById(id).isPresent()){

            return ResponseEntity.status(HttpStatus.OK)
                    .body(affiliatedCompanyRepo.findById(id).get());

        }else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"ErrorMessage\": \"No Company registered with the Given ID:(\"}");

        }

    }

    @GetMapping("findCompany")
    public List<AffiliatedCompany> getCompany(@RequestBody ObjectNode objectNode){

        String name = objectNode.get("Affiliate_Name").asText();
//        Long id = objectNode.get("Affiliate_Id").asLong();
        String pos = objectNode.get("Affiliate_Position").asText();
        if (affiliatedCompanyRepo.findByAffiliateNameAndAffiliatePosition(name, pos).isEmpty()){

            System.out.println("No Company registered with this Affiliate!!");
            return null;
        }else {
            return affiliatedCompanyRepo.findByAffiliateNameAndAffiliatePosition(name, pos);
        }



    }


    @PostMapping("detail")
    public ResponseEntity<?> details(@RequestBody ObjectNode objectNode){

        Long id = objectNode.get("parentId").asLong();
        String pos = objectNode.get("parentPosition").asText();

//        String AC = affiliatedCompanyRepo.findByAffiliateIdAndAffiliatePosition(id, pos).toString();
//        System.out.println(AC);
//
//        new Gson().toJson(affiliatedCompanyRepo.findByAffiliateIdAndAffiliatePosition(id, pos));
//
//        String Ch = childrenRepo.findByParentsIdAndParentsPosition(id, pos).toString();
//        System.out.println(Ch);

        String AC = new Gson().toJson(affiliatedCompanyRepo.findByAffiliateIdAndAffiliatePosition(id, pos));
        String Ch = new Gson().toJson(childrenRepo.findByParentsIdAndParentsPosition(id, pos));

        String fullJSON = AC.concat(Ch);


        return ResponseEntity.status(HttpStatus.OK)
                .body("{\"Affiliated_Company\":"+AC+", \"Children\": "+Ch+"}");
    }


    @GetMapping("detail/{id}/{pos}")
    public ResponseEntity<?> detailsFromPath(@PathVariable Long id,  @PathVariable String pos){

//        Long id = objectNode.get("parentId").asLong();
//        String pos = objectNode.get("parentPosition").asText();

//        String AC = affiliatedCompanyRepo.findByAffiliateIdAndAffiliatePosition(id, pos).toString();
//        System.out.println(AC);
//
//        new Gson().toJson(affiliatedCompanyRepo.findByAffiliateIdAndAffiliatePosition(id, pos));
//
//        String Ch = childrenRepo.findByParentsIdAndParentsPosition(id, pos).toString();
//        System.out.println(Ch);

        String AC = new Gson().toJson(affiliatedCompanyRepo.findByAffiliateIdAndAffiliatePosition(id, pos));
        String Ch = new Gson().toJson(childrenRepo.findByParentsIdAndParentsPosition(id, pos));

        String fullJSON = AC.concat(Ch);


        return ResponseEntity.status(HttpStatus.OK)
                .body("{\"Affiliated_Company\":"+AC+", \"Children\": "+Ch+"}");
    }


    @PutMapping("update/{id}")
    public ResponseEntity<?> updateAC(@PathVariable Long id, @RequestBody AffiliatedCompany ACWithUpData){

        if(affiliatedCompanyRepo.findById(id).isPresent()){

            AffiliatedCompany affiliatedCompany = affiliatedCompanyRepo.findById(id).get();


            /******************Storing history before Update********************/
            ACHistory acHistory = new ACHistory();
            acHistory.setAction("UPDATE");
            acHistory.setAffiliateId(affiliatedCompany.getAffiliateId());
            acHistory.setAffiliateName(affiliatedCompany.getAffiliateName());
            acHistory.setAffiliatePosition(affiliatedCompany.getAffiliatePosition());
            acHistory.setCompanyName(affiliatedCompany.getCompanyName());
            acHistory.setDate(ZonedDateTime.now(ZoneId.of("Africa/Addis_Ababa")));
            acHistory.setShareAmount(affiliatedCompany.getShareAmount());
            acHistory.setUserId(affiliatedCompany.getId());


            achRepo.save(acHistory);




            /********************************************************************/


            affiliatedCompany.setAffiliateName(ACWithUpData.getAffiliateName());
            affiliatedCompany.setAffiliatePosition(ACWithUpData.getAffiliatePosition());
            affiliatedCompany.setShareAmount(ACWithUpData.getShareAmount());
            affiliatedCompany.setCompanyName(ACWithUpData.getCompanyName());
            affiliatedCompany.setAffiliateId(ACWithUpData.getAffiliateId());

            affiliatedCompanyRepo.save(affiliatedCompany);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(affiliatedCompany);


        }else {

            System.out.println("AM in AC");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"ErrorMessage\": \"No Company registered with the Given ID:(\"}");

        }

    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteAC(@PathVariable Long id){

        if (affiliatedCompanyRepo.findById(id).isPresent()){

            AffiliatedCompany ACToDelete = affiliatedCompanyRepo.findById(id).get();

            /******************Storing history before Update********************/
            ACHistory acHistory = new ACHistory();
            acHistory.setAction("DELETE");
            acHistory.setAffiliateId(ACToDelete.getAffiliateId());
            acHistory.setAffiliateName(ACToDelete.getAffiliateName());
            acHistory.setAffiliatePosition(ACToDelete.getAffiliatePosition());
            acHistory.setCompanyName(ACToDelete.getCompanyName());
            acHistory.setDate(ZonedDateTime.now(ZoneId.of("Africa/Addis_Ababa")));
            acHistory.setShareAmount(ACToDelete.getShareAmount());
            acHistory.setUserId(ACToDelete.getId());


            achRepo.save(acHistory);



            /********************************************************************/

            affiliatedCompanyRepo.delete(ACToDelete);

            int sizeOfCompanies = affiliatedCompanyRepo.findByAffiliateIdAndAffiliatePosition(ACToDelete.getAffiliateId(), ACToDelete.getAffiliatePosition()).size();

            if (ACToDelete.getAffiliatePosition().equals("EM")){

                if (executiveManagementRepo.findById(ACToDelete.getAffiliateId()).isPresent()){

                    ExecutiveManagement em1 = executiveManagementRepo.findById(ACToDelete.getAffiliateId()).get();
                    em1.setNumberOfAC(sizeOfCompanies);
                    executiveManagementRepo.save(em1);

                }else {

                    System.out.println("There is no EM found!");

                }

            } else if (ACToDelete.getAffiliatePosition().equals("TSH")) {

                if (topShareHoldersRepo.findById(ACToDelete.getAffiliateId()).isPresent()){

                    TopShareHolders TSH1 = topShareHoldersRepo.findById(ACToDelete.getAffiliateId()).get();
                    TSH1.setNumberOfAC(sizeOfCompanies);
                    topShareHoldersRepo.save(TSH1);

                }else {

                    System.out.println("There is no TSH found!");

                }

            }

            return ResponseEntity.status(HttpStatus.OK)
                    .body("{\"SuccessMessage\": \"SuccessFully Deleted "+ACToDelete.getCompanyName()+"\"}");

        }else {
            System.out.println("Am here");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"ErrorMessage\": \"No Company registered with the Given ID:(\"}");

        }

    }






    /*********************************/




}
