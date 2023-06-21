package com.example.rpeal.Controller;

import com.example.rpeal.Model.BoardMembers;
import com.example.rpeal.Model.ExecutiveManagement;
import com.example.rpeal.Repository.BoardMembersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/RPEAL/BM/")
public class BoardMembersController {


    /**Required Fields**/

    private BoardMembersRepo boardMembersRepo;

    /**********************/

    /**Autowiring**/

    @Autowired
    public void setBoardMembersRepo(BoardMembersRepo boardMembersRepo) {
        this.boardMembersRepo = boardMembersRepo;
    }

    /*******************/


    /**Methods**/


    @PostMapping("save")
    public BoardMembers postBM(@RequestBody BoardMembers boardMembers){

        return boardMembersRepo.save(boardMembers);

    }

    @GetMapping("findAll")
    public List<BoardMembers> getAllBM(){
        return boardMembersRepo.findAll();
    }

    /********************************/


}
