package com.example.rpeal.Controller;

import com.example.rpeal.Model.BMHistory;
import com.example.rpeal.Model.BoardMembers;
import com.example.rpeal.Model.DeletedBM;
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
@RequestMapping(path = "/RPEAL/BM/")
public class BoardMembersController {


    /**Required Fields**/

    private BMHRepo bmhRepo;
    private deletedBMRepo deletedBMRepo;

    private BoardMembersRepo boardMembersRepo;
//    private AffiliatedCompanyRepo affiliatedCompanyRepo;

    /**********************/

    /**Autowiring**/

    @Autowired
    public void setBoardMembersRepo(BoardMembersRepo boardMembersRepo) {
        this.boardMembersRepo = boardMembersRepo;
    }

    @Autowired
    public void setDeletedBMRepo(com.example.rpeal.Repository.deletedBMRepo deletedBMRepo) {
        this.deletedBMRepo = deletedBMRepo;
    }

    @Autowired
    public void setBmhRepo(BMHRepo bmhRepo) {
        this.bmhRepo = bmhRepo;
    }


    //    @Autowired
//    public void setAffiliatedCompanyRepo(AffiliatedCompanyRepo affiliatedCompanyRepo) {
//        this.affiliatedCompanyRepo = affiliatedCompanyRepo;
//    }

    /*******************/


    /**Methods**/


    @PostMapping("save")
    public BoardMembers postBM(@RequestBody BoardMembers boardMembers){

        boardMembers.setPosition("BM");
        return boardMembersRepo.save(boardMembers);

    }

    @GetMapping("findAll")
    public List<BoardMembers> getAllBM(){
        return boardMembersRepo.findAll();
    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteBM(@PathVariable Long id){

        if (boardMembersRepo.findById(id).isPresent()){

            BoardMembers boardMembers = boardMembersRepo.findById(id).get();

            /******************Storing history before Update********************/
            BMHistory bmHistory = new BMHistory();
            DeletedBM deletedBM = new DeletedBM();

            bmHistory.setAction("DELETE");
            bmHistory.setDate(ZonedDateTime.now(ZoneId.of("Africa/Addis_Ababa")));
            bmHistory.setUserId(boardMembers.getId());
            bmHistory.setNameOfTheBoardMember(boardMembers.getNameOfTheBoardMember());
            bmHistory.setPosition(boardMembers.getPosition());
            bmHistory.setRelatedCompanies(boardMembers.getRelatedCompanies());
            bmHistory.setRelation(boardMembers.getRelation());

            deletedBM.setAction("DELETE");
            deletedBM.setDate(ZonedDateTime.now(ZoneId.of("Africa/Addis_Ababa")));
            deletedBM.setId(boardMembers.getId());
            deletedBM.setNameOfTheBoardMember(boardMembers.getNameOfTheBoardMember());
            deletedBM.setPosition(boardMembers.getPosition());
            deletedBM.setRelatedCompanies(boardMembers.getRelatedCompanies());
            deletedBM.setRelation(boardMembers.getRelation());


            bmhRepo.save(bmHistory);
            deletedBMRepo.save(deletedBM);



            /********************************************************************/

            boardMembersRepo.delete(boardMembers);

            return ResponseEntity.status(HttpStatus.OK)
                    .body("{\"SuccessMessage\": \"SuccessFully Deleted "+boardMembers.getNameOfTheBoardMember()+"\"}");

        }else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"ErrorMessage\": \"No Board member registered with the Given ID:(\"}");

        }

    }


    @PutMapping("update/{id}")
    public ResponseEntity<?> updateBM(@RequestBody BoardMembers bmToUpdate, @PathVariable Long id){



        if(boardMembersRepo.findById(id).isPresent()){

            BoardMembers bm = boardMembersRepo.findById(id).get();


            /******************Storing history before Update********************/
            BMHistory bmHistory = new BMHistory();
            bmHistory.setAction("UPDATE");
            bmHistory.setDate(ZonedDateTime.now(ZoneId.of("Africa/Addis_Ababa")));
            bmHistory.setUserId(bm.getId());
            bmHistory.setNameOfTheBoardMember(bm.getNameOfTheBoardMember());
            bmHistory.setPosition(bm.getPosition());
            bmHistory.setRelatedCompanies(bm.getRelatedCompanies());
            bmHistory.setRelation(bm.getRelation());


            bmhRepo.save(bmHistory);



            /********************************************************************/


            bm.setPosition("BM");
            bm.setNameOfTheBoardMember(bmToUpdate.getNameOfTheBoardMember());
            bm.setRelation(bmToUpdate.getRelation());
            bm.setRelatedCompanies(bmToUpdate.getRelatedCompanies());


            return ResponseEntity.status(HttpStatus.OK)
                    .body(boardMembersRepo.save(bm));

        }else {

            System.out.println("The user" + " " + bmToUpdate.getNameOfTheBoardMember() + " is not found. Please provide the correct user name.");
            return ResponseEntity.status(HttpStatus.OK)
                    .body("{\"errors\":[{\"actionMessage\": \"The user " + bmToUpdate.getNameOfTheBoardMember() + " is not found. Please provide the correct user name.\"}]}");

        }


    }



    @GetMapping("{id}")
    public ResponseEntity<?> getOneBM(@PathVariable Long id){

        if(boardMembersRepo.findById(id).isPresent()){

            return ResponseEntity.status(HttpStatus.OK)
                    .body(boardMembersRepo.findById(id).get());

        }else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"ErrorMessage\": \"No Child registered with the Given ID:(\"}");

        }

    }

    /********************************/


}
