package com.example.rpeal.Controller;


import com.example.rpeal.Model.*;
import com.example.rpeal.Repository.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/RPEAL/history/")
public class HistoryController {

    /*****************Repos*******************/

    private EMHRepo emhRepo;
    private ACHRepo achRepo;
    private TSHHRepo tshhRepo;
    private ChHRepo chHRepo;
    private BMHRepo bmhRepo;

    private deletedTSHRepo deletedTSHRepo;
    private deletedBMRepo deletedBMRepo;
    private deletedEMRepo deletedEMRepo;


    /********************************************/


    /*****************Autowiring*******************/


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

    @Autowired
    public void setDeletedTSHRepo(com.example.rpeal.Repository.deletedTSHRepo deletedTSHRepo) {
        this.deletedTSHRepo = deletedTSHRepo;
    }

    @Autowired
    public void setDeletedBMRepo(com.example.rpeal.Repository.deletedBMRepo deletedBMRepo) {
        this.deletedBMRepo = deletedBMRepo;
    }

    @Autowired
    public void setDeletedEMRepo(com.example.rpeal.Repository.deletedEMRepo deletedEMRepo) {
        this.deletedEMRepo = deletedEMRepo;
    }

    /********************************************/




    @GetMapping("{position}/{id}")
    public ResponseEntity<?> UD_History(@PathVariable Long id, @PathVariable String position) throws IOException {

    List<ACHistory> acHistory = achRepo.findByAffiliateIdAndAffiliatePosition(id,position);

    List<ChHistory> chHistories = chHRepo.findByParentsIdAndParentsPosition(id,position);

    List<EMHistory> emHistories = emhRepo.findAllByUserId(id);

    List<BMHistory> bmHistories = bmhRepo.findAllByUserId(id);

    List<TSHHistory> tshHistories = tshhRepo.findAllByUserId(id);


        SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");

        ObjectMapper ow = new ObjectMapper();
        ow.registerModule(new JavaTimeModule());

        ow.setDateFormat(formater);

        String jsonACH = ow.writeValueAsString(acHistory);
        String jsonCHH = ow.writeValueAsString(chHistories);
        String jsonEMH = ow.writeValueAsString(emHistories);
        String jsonBMH = ow.writeValueAsString(bmHistories);
        String jsonTSHH = ow.writeValueAsString(tshHistories);


//    ObjectMapper Obj = new ObjectMapper();
//
//    String new_acHistory = Obj.writeValueAsString(acHistory);
//    System.out.println(new_acHistory);
//    String new_chHistories = Obj.writeValueAsString(chHistories);



        if (position.equals("EM")){

            return ResponseEntity.status(HttpStatus.OK)
                    .body("{\"EM\":"+jsonEMH.concat(",").concat("\"Companies\":").concat(jsonACH).concat(",").concat("\"children\":").concat(jsonCHH).concat("}"));

        } else if (position.equals("TSH")) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("{\"TSH\":"+jsonTSHH.concat(",").concat("\"Companies\":").concat(jsonACH).concat(",").concat("\"children\":").concat(jsonCHH).concat("}"));
        }else {

            return ResponseEntity.status(HttpStatus.OK)
                    .body("{\"BM\":"+jsonBMH.concat(",").concat("\"Companies\":").concat(jsonACH).concat(",").concat("\"children\":").concat(jsonCHH).concat("}"));

        }


    }
//    new_acHistory.concat(",").concat(new_chHistories)
//    "{"+acHistory+","+chHistories+"}"

    @GetMapping("deleteHistory")
    public ResponseEntity<?> allDeletedData() throws JsonProcessingException {

        List<DeletedEM> deletedEM = deletedEMRepo.findAll();
        List<DeletedTSH> deletedTSH = deletedTSHRepo.findAll();
        List<DeletedBM> deletedBM = deletedBMRepo.findAll();


        SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");

        ObjectMapper ow = new ObjectMapper();
        ow.registerModule(new JavaTimeModule());

        ow.setDateFormat(formater);

        String jsonDeletedEM = ow.writeValueAsString(deletedEM);
        String jsonDeletedTSH = ow.writeValueAsString(deletedTSH);
        String jsonDeletedBM = ow.writeValueAsString(deletedBM);





        return ResponseEntity.status(HttpStatus.OK)
                .body("{\"EM\":"+jsonDeletedEM.concat(",").concat("\"TSH\":").concat(jsonDeletedTSH).concat(",").concat("\"BM\":").concat(jsonDeletedBM).concat("}"));


    }


}



