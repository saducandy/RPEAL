package com.example.rpeal.Controller;

import com.example.rpeal.Model.DeletedTSH;
import com.example.rpeal.Model.TSHHistory;
import com.example.rpeal.Model.TopShareHolders;
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
@RequestMapping(path = "/RPEAL/TSH")
public class TopShareHoldersController {


    /**Required Fields**/

    private TopShareHoldersRepo topShareHoldersRepo;
    private deletedTSHRepo deletedTSHRepo;
    private AffiliatedCompanyRepo affiliatedCompanyRepo;

    private EMHRepo emhRepo;
    private ACHRepo achRepo;
    private TSHHRepo tshhRepo;
    private ChHRepo chHRepo;
    private BMHRepo bmhRepo;

    /************************/



    /**AutoWiring**/

    @Autowired
    public void setAffiliatedCompanyRepo(AffiliatedCompanyRepo affiliatedCompanyRepo) {
        this.affiliatedCompanyRepo = affiliatedCompanyRepo;
    }

    @Autowired
    public void setTopShareHoldersRepo(TopShareHoldersRepo topShareHoldersRepo) {
        this.topShareHoldersRepo = topShareHoldersRepo;
    }

    @Autowired
    public void setDeletedTSHRepo(com.example.rpeal.Repository.deletedTSHRepo deletedTSHRepo) {
        this.deletedTSHRepo = deletedTSHRepo;
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

    /*****************/


    /**Methods**/


    @PostMapping("save")
    public TopShareHolders postTSH(@RequestBody TopShareHolders topShareHolders){



//        if (affiliatedCompanyRepo.findByAffiliateNameAndAffiliatePosition(topShareHolders.getNameOfShareHolder(), "TSH") !=null){
//
//            int numberOfAC = affiliatedCompanyRepo.findByAffiliateNameAndAffiliatePosition(topShareHolders.getNameOfShareHolder(), "TSH").size();
//
//            topShareHolders.setNumberOfAC(numberOfAC);
//
//        }else {
//
//            topShareHolders.setNumberOfAC(0);
//
//        }
        topShareHolders.setNumberOfAC(0);
        topShareHolders.setNumberOfChildren(0);
        topShareHolders.setPosition("TSH");


        return topShareHoldersRepo.save(topShareHolders);



    }

    @GetMapping("findAll")
    public List<TopShareHolders> getAllTSH(){
        return topShareHoldersRepo.findAll();
    }



    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteTSH(@PathVariable Long id){

        if (topShareHoldersRepo.findById(id).isPresent()){

            TopShareHolders topShareHolders = topShareHoldersRepo.findById(id).get();


            /******************Storing history before Update********************/
            TSHHistory tshHistory = new TSHHistory();
            DeletedTSH deletedTSH = new DeletedTSH();

            tshHistory.setAction("DELETE");
            tshHistory.setDate(ZonedDateTime.now(ZoneId.of("Africa/Addis_Ababa")));
            tshHistory.setUserId(topShareHolders.getId());
            tshHistory.setNameOfShareHolder(topShareHolders.getNameOfShareHolder());
            tshHistory.setNameOfSpouse(topShareHolders.getNameOfSpouse());
            tshHistory.setNumberOfAC(topShareHolders.getNumberOfAC());
            tshHistory.setNumberOfChildren(topShareHolders.getNumberOfChildren());
            tshHistory.setPosition(topShareHolders.getPosition());
            tshHistory.setShareAmountOfTheHolder(topShareHolders.getShareAmountOfTheHolder());
            tshHistory.setShareAmountOfTheSpouse(topShareHolders.getShareAmountOfTheSpouse());

            deletedTSH.setAction("DELETE");
            deletedTSH.setDate(ZonedDateTime.now(ZoneId.of("Africa/Addis_Ababa")));
            deletedTSH.setId(topShareHolders.getId());
            deletedTSH.setNameOfShareHolder(topShareHolders.getNameOfShareHolder());
            deletedTSH.setNameOfSpouse(topShareHolders.getNameOfSpouse());
            deletedTSH.setNumberOfAC(topShareHolders.getNumberOfAC());
            deletedTSH.setNumberOfChildren(topShareHolders.getNumberOfChildren());
            deletedTSH.setPosition(topShareHolders.getPosition());
            deletedTSH.setShareAmountOfTheHolder(topShareHolders.getShareAmountOfTheHolder());
            deletedTSH.setShareAmountOfTheSpouse(topShareHolders.getShareAmountOfTheSpouse());


            tshhRepo.save(tshHistory);
            deletedTSHRepo.save(deletedTSH);


            /********************************************************************/


            topShareHoldersRepo.delete(topShareHolders);

            return ResponseEntity.status(HttpStatus.OK)
                    .body("{\"SuccessMessage\": \"SuccessFully Deleted "+topShareHolders.getNameOfShareHolder()+"\"}");

        }else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"ErrorMessage\": \"No Share holder registered with the Given ID:(\"}");

        }

    }



    @PutMapping("update/{id}")
    public ResponseEntity<?> updateTSH(@RequestBody TopShareHolders TSHToUpdate, @PathVariable Long id){

        if(topShareHoldersRepo.findById(id).isPresent()){

            TopShareHolders tsh = topShareHoldersRepo.findById(id).get();


            /******************Storing history before Update********************/
            TSHHistory tshHistory = new TSHHistory();
            tshHistory.setAction("UPDATE");
            tshHistory.setDate(ZonedDateTime.now(ZoneId.of("Africa/Addis_Ababa")));
            tshHistory.setUserId(tsh.getId());
            tshHistory.setNameOfShareHolder(tsh.getNameOfShareHolder());
            tshHistory.setNameOfSpouse(tsh.getNameOfSpouse());
            tshHistory.setNumberOfAC(tsh.getNumberOfAC());
            tshHistory.setNumberOfChildren(tsh.getNumberOfChildren());
            tshHistory.setPosition(tsh.getPosition());
            tshHistory.setShareAmountOfTheHolder(tsh.getShareAmountOfTheHolder());
            tshHistory.setShareAmountOfTheSpouse(tsh.getShareAmountOfTheSpouse());


            tshhRepo.save(tshHistory);


            /********************************************************************/


            tsh.setNameOfShareHolder(TSHToUpdate.getNameOfShareHolder());
            tsh.setShareAmountOfTheHolder(TSHToUpdate.getShareAmountOfTheHolder());
            tsh.setNameOfSpouse(TSHToUpdate.getNameOfSpouse());
            tsh.setShareAmountOfTheSpouse(TSHToUpdate.getShareAmountOfTheSpouse());
//            tsh.setNumberOfChildren(TSHToUpdate.getNumberOfChildren());
//            tsh.setNumberOfAC(TSHToUpdate.getNumberOfAC());
            tsh.setPosition("TSH");


            return ResponseEntity.status(HttpStatus.OK)
                    .body(topShareHoldersRepo.save(tsh));

        }else {

            System.out.println("The user" + " " + TSHToUpdate.getNameOfShareHolder() + " is not found. Please provide the correct user name.");
            return ResponseEntity.status(HttpStatus.OK)
                    .body("{\"errors\":[{\"actionMessage\": \"The user " + TSHToUpdate.getNameOfShareHolder() + " is not found. Please provide the correct user name.\"}]}");

        }


    }


    @GetMapping("{id}")
    public ResponseEntity<?> getOneTSH(@PathVariable Long id){

        if(topShareHoldersRepo.findById(id).isPresent()){

            return ResponseEntity.status(HttpStatus.OK)
                    .body(topShareHoldersRepo.findById(id).get());

        }else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"ErrorMessage\": \"No TSH registered with the Given ID:(\"}");

        }

    }


    /**********************************/



}
