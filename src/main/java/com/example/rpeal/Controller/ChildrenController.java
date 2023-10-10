package com.example.rpeal.Controller;

import com.example.rpeal.Model.*;
import com.example.rpeal.Repository.*;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/RPEAL/Ch/")
public class ChildrenController {


    /**Required Fields**/

    private ChildrenRepo childrenRepo;
    private ExecutiveManagementRepo executiveManagementRepo;
    private TopShareHoldersRepo topShareHoldersRepo;
    private EMHRepo emhRepo;
    private ACHRepo achRepo;
    private TSHHRepo tshhRepo;
    private ChHRepo chHRepo;
    private BMHRepo bmhRepo;

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


        int sizeOfChildList = childrenRepo.findByParentsIdAndParentsPosition(children.getParentsId(), children.getParentsPosition()).size();

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


    @PutMapping("update/{id}")
    public ResponseEntity<?> updateChild(@PathVariable Long id, @RequestBody Children ChWithUpData){

        if(childrenRepo.findById(id).isPresent()){


            Children childToUp = childrenRepo.findById(id).get();

            /******************Storing history before Update********************/
            ChHistory childHistory = new ChHistory();
            childHistory.setUserId(childToUp.getId());
            childHistory.setFullNameOfTheChild(childToUp.getFullNameOfTheChild());
            childHistory.setFullNameOfTheFather(childToUp.getFullNameOfTheFather());
            childHistory.setParentsId(childToUp.getParentsId());
            childHistory.setParentsPosition(childToUp.getParentsPosition());
            childHistory.setShareAmount(childToUp.getShareAmount());
            childHistory.setAction("UPDATE");
            childHistory.setDate(ZonedDateTime.now(ZoneId.of("Africa/Addis_Ababa")));

            chHRepo.save(childHistory);



            /********************************************************************/


            childToUp.setFullNameOfTheChild(ChWithUpData.getFullNameOfTheChild());
            childToUp.setFullNameOfTheFather(ChWithUpData.getFullNameOfTheFather());
            childToUp.setShareAmount(ChWithUpData.getShareAmount());
            childToUp.setParentsPosition(ChWithUpData.getParentsPosition());
            childToUp.setParentsId(ChWithUpData.getParentsId());

            childrenRepo.save(childToUp);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(childToUp);


        }else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"ErrorMessage\": \"No Child registered with the Given ID:(\"}");

        }

    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteChild(@PathVariable Long id){

        if (childrenRepo.findById(id).isPresent()){

            Children ChToDelete = childrenRepo.findById(id).get();

            /******************Storing history before Update********************/
            ChHistory childHistory = new ChHistory();
            childHistory.setUserId(ChToDelete.getId());
            childHistory.setFullNameOfTheChild(ChToDelete.getFullNameOfTheChild());
            childHistory.setFullNameOfTheFather(ChToDelete.getFullNameOfTheFather());
            childHistory.setParentsId(ChToDelete.getParentsId());
            childHistory.setParentsPosition(ChToDelete.getParentsPosition());
            childHistory.setShareAmount(ChToDelete.getShareAmount());
            childHistory.setAction("DELETE");
            childHistory.setDate(ZonedDateTime.now(ZoneId.of("Africa/Addis_Ababa")));

            chHRepo.save(childHistory);



            /********************************************************************/



            childrenRepo.delete(ChToDelete);


            int sizeOfChildList = childrenRepo.findByParentsIdAndParentsPosition(ChToDelete.getParentsId(), ChToDelete.getParentsPosition()).size();

            if(ChToDelete.getParentsPosition().equals("EM")){

                if (executiveManagementRepo.findById(ChToDelete.getParentsId()).isPresent()){

                    ExecutiveManagement em = executiveManagementRepo.findById(ChToDelete.getParentsId()).get();
                    em.setNumberOfChildren(sizeOfChildList);
                    executiveManagementRepo.save(em);

                }else {

                    System.out.println("there is no child with this id!");

                }



            } else if (ChToDelete.getParentsPosition().equals("TSH")) {


                if (topShareHoldersRepo.findById(ChToDelete.getParentsId()).isPresent()){

                    TopShareHolders TSH = topShareHoldersRepo.findById(ChToDelete.getParentsId()).get();
                    TSH.setNumberOfChildren(sizeOfChildList);
                    topShareHoldersRepo.save(TSH);

                }else {

                    System.out.println("no child present with this id!");

                }


            }


            return ResponseEntity.status(HttpStatus.OK)
                    .body("{\"SuccessMessage\": \"SuccessFully Deleted "+ChToDelete.getFullNameOfTheChild()+"\"}");

        }else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"ErrorMessage\": \"No Child registered with the Given ID:(\"}");

        }

    }

    @GetMapping("{id}")
    public ResponseEntity<?> getOneCh(@PathVariable Long id){

        if(childrenRepo.findById(id).isPresent()){

            return ResponseEntity.status(HttpStatus.OK)
                    .body(childrenRepo.findById(id).get());

        }else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"ErrorMessage\": \"No Child registered with the Given ID:(\"}");

        }

    }



    /********************************/


}
