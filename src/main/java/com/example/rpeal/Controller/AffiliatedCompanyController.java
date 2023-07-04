package com.example.rpeal.Controller;

import com.example.rpeal.Model.AffiliatedCompany;
import com.example.rpeal.Model.BoardMembers;
import com.example.rpeal.Model.ExecutiveManagement;
import com.example.rpeal.Model.TopShareHolders;
import com.example.rpeal.Repository.AffiliatedCompanyRepo;
import com.example.rpeal.Repository.ChildrenRepo;
import com.example.rpeal.Repository.ExecutiveManagementRepo;
import com.example.rpeal.Repository.TopShareHoldersRepo;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    /*********************/


    /**Methods**/


    @PostMapping("save")
    public AffiliatedCompany postAC(@RequestBody AffiliatedCompany affiliatedCompany){

        if (affiliatedCompany.getAffiliatePosition().equals("EM")){

            if (executiveManagementRepo.findByNameOfEmployeeAndCurrentPosition(affiliatedCompany.getAffiliateName(), affiliatedCompany.getAffiliatePosition()) != null){

               ExecutiveManagement em1 = executiveManagementRepo.findByNameOfEmployeeAndCurrentPosition(affiliatedCompany.getAffiliateName(), affiliatedCompany.getAffiliatePosition());
               em1.setNumberOfAC(em1.getNumberOfAC() + 1);
               executiveManagementRepo.save(em1);

            }else {

                System.out.println("There is no EM found!");
                return null;

            }

        } else if (affiliatedCompany.getAffiliatePosition().equals("TSH")) {

            if (topShareHoldersRepo.findByNameOfShareHolder(affiliatedCompany.getAffiliateName()) != null){

                TopShareHolders TSH1 = topShareHoldersRepo.findByNameOfShareHolder(affiliatedCompany.getAffiliateName());
                TSH1.setNumberOfAC(TSH1.getNumberOfAC() + 1);
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


    @GetMapping("detail")
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
                .body("{\"Affiliated Company\":"+AC+", \"Children\": "+Ch+"}");
    }






    /*********************************/




}
