package com.example.rpeal.Controller;

import com.example.rpeal.Model.BoardMembers;
import com.example.rpeal.Model.Children;
import com.example.rpeal.Model.ExecutiveManagement;
import com.example.rpeal.Repository.ChildrenRepo;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/RPEAL/Ch/")
public class ChildrenController {


    /**Required Fields**/

    private ChildrenRepo childrenRepo;

    /***********************/

    /**Autowiring**/

    @Autowired
    public void setChildrenRepo(ChildrenRepo childrenRepo) {
        this.childrenRepo = childrenRepo;
    }

    /*******************/

    /**Methods**/


    @PostMapping("save")
    public Children postChild(@RequestBody Children children){

        return childrenRepo.save(children);

    }


    @GetMapping("findAll")
    public List<Children> getAllChild(){
        return childrenRepo.findAll();
    }


    @GetMapping("findChild")
    public List<Children> getChild(@RequestBody ObjectNode objectNode){

        String fullFatherName = objectNode.get("Father_Name").asText();
//        String parentsPosition = objectNode.get("Position_Type").asText();
        Long parentsId = objectNode.get("Parents_Id").asLong();




        if (childrenRepo.findByFullNameOfTheFatherAndParentsId(fullFatherName, parentsId).isEmpty()) {

            System.out.println("No child registered with a given parent name!!!");
            return null;

        }else {

            System.out.println("here");
            return childrenRepo.findByFullNameOfTheFatherAndParentsId(fullFatherName, parentsId);
        }

    }



    /********************************/


}
