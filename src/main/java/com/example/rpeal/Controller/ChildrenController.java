package com.example.rpeal.Controller;

import com.example.rpeal.Model.BoardMembers;
import com.example.rpeal.Model.Children;
import com.example.rpeal.Model.ExecutiveManagement;
import com.example.rpeal.Model.TopShareHolders;
import com.example.rpeal.Repository.ChildrenRepo;
import com.example.rpeal.Repository.ExecutiveManagementRepo;
import com.example.rpeal.Repository.TopShareHoldersRepo;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/RPEAL/Ch/")
public class ChildrenController {


    /**Required Fields**/

    private ChildrenRepo childrenRepo;
    private ExecutiveManagementRepo executiveManagementRepo;
    private TopShareHoldersRepo topShareHoldersRepo;

    /***********************/

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

    /*******************/

    /**Methods**/


    @PostMapping("save")
    public Children postChild(@RequestBody Children children){


//        if (children.getParentsPosition().equals("EM")){
//
//            if (childrenRepo.findByFullNameOfTheFatherAndParentsPosition(children.getFullNameOfTheFather(), "EM") != null){
//
//                int sizeOfChildList = childrenRepo.findByFullNameOfTheFatherAndParentsPosition(children.getFullNameOfTheFather(), "EM").size();
//
//
//            }else {
//
//                System.out.println("No child found with the given Info!");
//                return null;
//
//            }
//
//        } else if (children.getParentsPosition().equals("TSH")) {
//
//            if ()
//
//        }

        childrenRepo.save(children);


        int sizeOfChildList = childrenRepo.findByFullNameOfTheFatherAndParentsPosition(children.getFullNameOfTheFather(), children.getParentsPosition()).size();

        if(children.getParentsPosition().equals("EM")){

            if (executiveManagementRepo.findById(children.getParentsId()).isPresent()){

                ExecutiveManagement em = executiveManagementRepo.findById(children.getParentsId()).get();
                em.setNumberOfChildren(sizeOfChildList);
                executiveManagementRepo.save(em);
                return children;

            }else {

                System.out.println("there is no child with this id!");

            }



        } else if (children.getParentsPosition().equals("TSH")) {


            if (topShareHoldersRepo.findById(children.getParentsId()).isPresent()){

                TopShareHolders TSH = topShareHoldersRepo.findById(children.getParentsId()).get();
                TSH.setNumberOfChildren(sizeOfChildList);
                topShareHoldersRepo.save(TSH);
                return children;

            }else {

                System.out.println("no child present with this id!");
                return null;

            }


        }


        return children;
    }


    @GetMapping("findAll")
    public List<Children> getAllChild(){
        return childrenRepo.findAll();
    }


    @GetMapping("findChild")
    public List<Children> getChild(@RequestBody ObjectNode objectNode){

        String fullFatherName = objectNode.get("Father_Name").asText();
        String parentsPosition = objectNode.get("Position_Type").asText();





        if (childrenRepo.findByFullNameOfTheFatherAndParentsPosition(fullFatherName, parentsPosition).isEmpty()) {

            System.out.println("No child registered with a given parent name!!!");
            return null;

        }else {

            System.out.println("here");
            return childrenRepo.findByFullNameOfTheFatherAndParentsPosition(fullFatherName, parentsPosition);
        }

    }



    /********************************/


}
