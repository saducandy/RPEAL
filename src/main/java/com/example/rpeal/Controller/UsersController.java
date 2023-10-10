package com.example.rpeal.Controller;

import com.example.rpeal.Model.AffiliatedCompany;
import com.example.rpeal.Model.Children;
import com.example.rpeal.Model.TopShareHolders;
import com.example.rpeal.Model.Users;
import com.example.rpeal.Repository.UsersRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import netscape.javascript.JSObject;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/RPEAL/users")
public class UsersController {

    private UsersRepo usersRepo;
    private Users whichUser;


    @Autowired
    public void setUsersRepo(UsersRepo usersRepo) {
        this.usersRepo = usersRepo;
    }



    @PostMapping("save")
    public Users postUserByAdmin(@RequestBody Users userToSave){


        return usersRepo.save(userToSave);

    }

    @PutMapping("update")
    public ResponseEntity<?> updateUserByAdmin(@RequestBody Users updatedUserData){

        if(usersRepo.findByUserName(updatedUserData.getUserName()) != null){

            Users userToUpdate = usersRepo.findByUserName(updatedUserData.getUserName());
            userToUpdate.setUserName(updatedUserData.getUserName());
            userToUpdate.setRole(updatedUserData.getRole());
            userToUpdate.setPassWord(updatedUserData.getPassWord());

            return ResponseEntity.status(HttpStatus.OK)
                    .body(usersRepo.save(userToUpdate));

        }else {

            System.out.println("The user" + " " + updatedUserData.getUserName() + " is not found. Please provide the correct user name.");
            return ResponseEntity.status(HttpStatus.OK)
                    .body("{\"errors\":[{\"actionMessage\": \"The user " + updatedUserData.getUserName() + " is not found. Please provide the correct user name.\"}]}");

        }


    }

    @GetMapping("findAll")
    public List<Users> getAllUsers(){

        return usersRepo.findAll();

    }

    @PostMapping("login")
    public ResponseEntity<?> checkUser(@RequestBody ObjectNode objectNode){

        String un = objectNode.get("userName").asText();
        String pw = objectNode.get("passWord").asText();

        if (usersRepo.findByUserNameAndPassWord(un, pw) != null){

            Users foundUser = usersRepo.findByUserNameAndPassWord(un, pw);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(foundUser);

        }else {

            System.out.println("Please provide the correct username or password!");
            return ResponseEntity.status(HttpStatus.OK)
                    .body("{\"errors\":[{\"actionMessage\": \"Please provide the correct userName or password:(\"}]}");

        }

    }

    @GetMapping("findOneUser")
    public ResponseEntity<?> getSingleUser(@RequestBody ObjectNode objectNode){

        String un = objectNode.get("userName").asText();

        if (usersRepo.findByUserName(un) != null){

            return ResponseEntity.status(HttpStatus.OK)
                    .body(usersRepo.findByUserName(un));

        }else {

            return ResponseEntity.status(HttpStatus.OK)
                    .body("{\"errors\":[{\"actionMessage\": \"Please provide the correct userName:(\"}]}");

        }

    }


    @DeleteMapping("delete")
    public ResponseEntity<?> deleteUsers(@RequestBody ObjectNode objectNode){

        if (!objectNode.get("userName").asText().isEmpty()){

            String un = objectNode.get("userName").asText();
            if(usersRepo.findByUserName(un) != null){

                usersRepo.delete(usersRepo.findByUserName(un));
                return ResponseEntity.status(HttpStatus.OK)
                        .body("{\"errors\":[],\"Message\":\"Successfully deleted a User!\", \"Deleted User:\":\""+un+"\"}");


            }else {

                return ResponseEntity.status(HttpStatus.OK)
                        .body("{\"errors\":[{\"actionMessage\": \"Please provide the correct userName:(\"}]}");

            }


        }else {

            Long deletedUsers = usersRepo.count();
            usersRepo.deleteAll();
            return ResponseEntity.status(HttpStatus.OK)
                    .body("{\"errors\":[],\"Message\":\"Successfully deleted all Users!\", \"Number of deleted users:\":\""+deletedUsers+"\"}");

        }

    }









}
