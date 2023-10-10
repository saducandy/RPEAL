package com.example.rpeal.Controller;


import com.example.rpeal.Model.AffiliatedCompany;
import com.example.rpeal.Model.Children;
import com.example.rpeal.Model.ExecutiveManagement;
import com.example.rpeal.Model.TopShareHolders;
import com.example.rpeal.Repository.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

//import net.sf.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/RPEAL/AllDetails")
public class AllDetailsController {

    private AffiliatedCompanyRepo affiliatedCompanyRepo;
    private ChildrenRepo childrenRepo;
    private BoardMembersRepo boardMembersRepo;
    private ExecutiveManagementRepo executiveManagementRepo;
    private TopShareHoldersRepo topShareHoldersRepo;
    private UsersRepo usersRepo;
    private EMHRepo emhRepo;
    private ACHRepo achRepo;
    private TSHHRepo tshhRepo;
    private ChHRepo chHRepo;
    private BMHRepo bmhRepo;
    String jsonEMSTR;
    String ch_ARR;
    String ac_ARR;
    String emArray_str;
    String check;

    String lableCH = "\"child";
    String lableAC = "\"company";

    String colon = ":";
    String quote = "\"";

    String startLabel = "{";
    String middleLabel = "\",";
    String endLabel = "\"}";
    String comma = ",";


    String strResultEMCH;
    String strResultEMAC;

    String[] testArray = new String[1];


    String openCB = "{";
    String closeCB = "}";
    String openCloseQ = "\"";
    String squareBO = "[";
    String squareBC = "]";


    String json1;
    String json2;
    String json3;
    String json4;



    @Autowired
    public void setAffiliatedCompanyRepo(AffiliatedCompanyRepo affiliatedCompanyRepo) {
        this.affiliatedCompanyRepo = affiliatedCompanyRepo;
    }

    @Autowired
    public void setChildrenRepo(ChildrenRepo childrenRepo) {
        this.childrenRepo = childrenRepo;
    }

    @Autowired
    public void setBoardMembersRepo(BoardMembersRepo boardMembersRepo) {
        this.boardMembersRepo = boardMembersRepo;
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
    public void setUsersRepo(UsersRepo usersRepo) {
        this.usersRepo = usersRepo;
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

    /*********************************************************************************************************************************************/



    @GetMapping("em")
    public ResponseEntity<?> EMFullDetails() throws JsonProcessingException, JSONException {

        long i = executiveManagementRepo.count();
        List<ExecutiveManagement> allEM= executiveManagementRepo.findAll();
        String[] em_Array = new String[(int)i];






        for (long j = 0; j < i; j++) {


            ExecutiveManagement em = allEM.get((int) j);
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            this.jsonEMSTR = ow.writeValueAsString(em);
            check = jsonEMSTR;






            org.json.JSONObject jsonObject = new org.json.JSONObject(jsonEMSTR);
            JSONObject jsonObject1 = new JSONObject();
            JSONObject jsonObject2 = new JSONObject();




//            emStr = em.toString();


            Long emID = em.getId();
            String emPos = em.getCurrentPosition();


            List<Children> allEM_CH = childrenRepo.findByParentsIdAndParentsPosition(emID, emPos);
            System.out.println(allEM_CH);

            List<AffiliatedCompany> allEM_AC = affiliatedCompanyRepo.findByAffiliateIdAndAffiliatePosition(emID, emPos);
            System.out.println(allEM_AC);

            String[] child_name_array = new String[allEM_CH.size()];
            String[] ac_name_array = new String[allEM_AC.size()];
            int l = allEM_AC.size() - 1;
            int t = allEM_CH.size() - 1;


            if (!allEM_CH.isEmpty()) {

                for (int k = 0; k < allEM_CH.size(); k++) {


                    System.out.println("hello");
                    String child_name = allEM_CH.get(k).getFullNameOfTheChild();
                    child_name_array[k] = child_name;



//                    jsonObject.put("children",jsonObject1.put("child",new JSONArray(new Object[] {} )));




//                    if (k == 0) {
//
//                        if(allEM_CH.size() == 1){
//
////                            strResultEMAC = startLabel.concat(lableAC).concat(quote).concat(colon).concat(quote).concat(child_name).concat(endLabel);
//                            jsonObject.put("child",child_name);
//
//                        }else {
//
////                            strResultEMAC = startLabel.concat(lableAC).concat(quote).concat(colon).concat(quote).concat(child_name).concat(middleLabel);
//
//
//                        }
//
//
//                        strResultEMCH = startLabel.concat(lableCH).concat(quote).concat(colon).concat(quote).concat(child_name).concat(middleLabel);
//
//                    } else if (k < t) {
//
//
//                        strResultEMCH = strResultEMCH.concat(lableCH).concat(quote).concat(colon).concat(quote).concat(child_name).concat(middleLabel);
//
//                    } else if (k == t) {
//
//                        strResultEMCH = strResultEMCH.concat(lableCH).concat(quote).concat(colon).concat(quote).concat(child_name).concat(endLabel);
//
//                    }


//                child_name_array[k] = child_name;


//                System.out.println(Arrays.toString(child_name_array));

                }

                jsonObject.put("children",new JSONArray(child_name_array));

            }

//            child_name_array[0] = strResultEMCH;
////            strResultEMCH = "";


            if (!allEM_AC.isEmpty()) {

            for (int r = 0; r < allEM_AC.size(); r++) {


                System.out.println("hi");
                String ac_name = allEM_AC.get(r).getCompanyName();
                ac_name_array[r] = ac_name;


//                ac_name_array[r] = ac_name;
//
//                System.out.println(Arrays.toString(ac_name_array));

//                if (r == 0) {
//
//                    if(allEM_AC.size() == 1){
//
//                        strResultEMAC = startLabel.concat(lableAC).concat(quote).concat(colon).concat(quote).concat(ac_name).concat(endLabel);
//
//                    }else {
//
//                        strResultEMAC = startLabel.concat(lableAC).concat(quote).concat(colon).concat(quote).concat(ac_name).concat(middleLabel);
//
//                    }
//
//
//                } else if (r < l) {
//
//                    strResultEMAC = strResultEMAC.concat(lableAC).concat(quote).concat(colon).concat(quote).concat(ac_name).concat(middleLabel);
//
//                } else if (r == l) {
//
//                    strResultEMAC = strResultEMAC.concat(lableAC).concat(quote).concat(colon).concat(quote).concat(ac_name).concat(endLabel);
//
//                }

            }

                jsonObject.put("companies",new JSONArray(ac_name_array));

        }

//            ac_name_array[0] = strResultEMAC;
////            strResultEMAC = "";




//            this.jsonEMSTR = "{\"EM\":["+this.jsonEMSTR+"], \"children\":"+Arrays.toString(child_name_array)+", \"companies\":"+Arrays.toString(ac_name_array)+"}";
//
//            if(!allEM_CH.isEmpty() && !allEM_AC.isEmpty()){
//
//                String prefixEM =
//                em_Array[(int)j] = this.jsonEMSTR.concat(comma).concat(strResultEMCH).concat(comma).concat(strResultEMAC);
//
//            } else if (allEM_CH.isEmpty() && !allEM_AC.isEmpty()) {
//
//                em_Array[(int)j] = this.jsonEMSTR.concat(comma).concat(strResultEMAC);
//
//            } else if (!allEM_CH.isEmpty() && allEM_AC.isEmpty()) {
//                em_Array[(int)j] = this.jsonEMSTR.concat(comma).concat(strResultEMCH);
//            } else if (allEM_CH.isEmpty() && allEM_AC.isEmpty()) {
//                em_Array[(int)j] = this.jsonEMSTR;
//            }
//
//            strResultEMAC = "";
//            strResultEMCH = "";

//            ch_ARR = Arrays.toString(child_name_array);
//            ac_ARR = Arrays.toString(ac_name_array);


            em_Array[(int)j] = jsonObject.toString();


        }



        emArray_str = Arrays.toString(em_Array);


        return ResponseEntity.status(HttpStatus.OK)
                .body(emArray_str);

    }


    @GetMapping("em_new")
    public ResponseEntity<?> EMFullDetailsNew() throws JsonProcessingException, JSONException {

        long i = executiveManagementRepo.count();
        List<ExecutiveManagement> allEM= executiveManagementRepo.findAll();
        String[] em_Array = new String[(int)i];




        for (long j = 0; j < i; j++) {


            ExecutiveManagement em = allEM.get((int) j);


            if (em.getNumberOfChildren() == 0 && em.getNumberOfAC() == 0){

                this.json1 = openCB.concat(openCloseQ).concat("id").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(em.getId().toString()).concat(openCloseQ).concat(comma)
                        .concat(openCloseQ).concat("nameOfEmployee").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(em.getNameOfEmployee()).concat(openCloseQ).concat(comma)
                        .concat(openCloseQ).concat("currentPosition").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(em.getCurrentPosition()).concat(openCloseQ).concat(comma)
                        .concat(openCloseQ).concat("nameOfSpouse").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(em.getNameOfSpouse()).concat(openCloseQ).concat(comma)
                        .concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(em.getNumberOfChildren())).concat(openCloseQ).concat(comma)
                        .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(em.getNumberOfAC())).concat(openCloseQ).concat(comma)
                        .concat(openCloseQ).concat("subRows").concat(openCloseQ).concat(colon).concat(squareBO).concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(0)).concat(openCloseQ).concat(comma)
                        .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(0)).concat(openCloseQ).concat(closeCB).concat(squareBC);

            } else {

                this.json1 = openCB.concat(openCloseQ).concat("id").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(em.getId().toString()).concat(openCloseQ).concat(comma)
                        .concat(openCloseQ).concat("nameOfEmployee").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(em.getNameOfEmployee()).concat(openCloseQ).concat(comma)
                        .concat(openCloseQ).concat("currentPosition").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(em.getCurrentPosition()).concat(openCloseQ).concat(comma)
                        .concat(openCloseQ).concat("nameOfSpouse").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(em.getNameOfSpouse()).concat(openCloseQ).concat(comma)
                        .concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(em.getNumberOfChildren())).concat(openCloseQ).concat(comma)
                        .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(em.getNumberOfAC())).concat(openCloseQ).concat(comma);

            }



            Long emID = em.getId();
            String emPos = em.getCurrentPosition();


            List<Children> allEM_CH = childrenRepo.findByParentsIdAndParentsPosition(emID, emPos);
            System.out.println(allEM_CH);

            List<AffiliatedCompany> allEM_AC = affiliatedCompanyRepo.findByAffiliateIdAndAffiliatePosition(emID, emPos);
            System.out.println(allEM_AC);


            int l = allEM_AC.size() - 1;
            int t = allEM_CH.size() - 1;


            if(allEM_AC.size() == allEM_CH.size()) {

                String[] child_name_array = new String[allEM_CH.size()];
                String[] ac_name_array = new String[allEM_AC.size()];


                for (int k = 0; k < allEM_CH.size(); k++) {


                    System.out.println("hello");
                    String child_name = allEM_CH.get(k).getFullNameOfTheChild();
                    child_name_array[k] = child_name;

                    String ac_name = allEM_AC.get(k).getCompanyName();
                    ac_name_array[k] = ac_name;


//                    testArray[0] = {name: adu};
//                    jsonObject.put("children", "adu");


                    if (k == 0) {

                        if (allEM_CH.size() == 1) {




                            json1 = json1.concat(openCloseQ).concat("subRows").concat(openCloseQ).concat(colon).concat(squareBO).concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(child_name).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(ac_name).concat(openCloseQ).concat(closeCB).concat(squareBC);


//                            jsonObject.put("child",child_name);


                        } else {

//                                strResultEMCH = startLabel.concat(lableAC).concat(quote).concat(colon).concat(quote).concat(child_name).concat(middleLabel);
                            json1 = json1.concat(openCloseQ).concat("subRows").concat(openCloseQ).concat(colon).concat(squareBO).concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(child_name).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(ac_name).concat(openCloseQ).concat(closeCB).concat(comma);


                        }


//                        strResultEMCH = startLabel.concat(lableCH).concat(quote).concat(colon).concat(quote).concat(child_name).concat(middleLabel);

                    } else if (k < t) {


//                        strResultEMCH = strResultEMCH.concat(lableCH).concat(quote).concat(colon).concat(quote).concat(child_name).concat(middleLabel);
                        json1 = json1.concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(child_name).concat(openCloseQ).concat(comma)
                                .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(ac_name).concat(openCloseQ).concat(closeCB).concat(comma);


                    } else if (k == t) {

                        json1 = json1.concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(child_name).concat(openCloseQ).concat(comma)
                                .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(ac_name).concat(openCloseQ).concat(closeCB).concat(squareBC);

                    }

//                    json1 = json1.concat(closeCB);
//                    em_Array[(int) j] = json1;


                }


            } else if (allEM_CH.size() > allEM_AC.size()) {

                String[] child_name_array = new String[allEM_CH.size()];
                String[] ac_name_array = new String[allEM_CH.size()];


                for (int k = 0; k < allEM_CH.size(); k++) {


                    System.out.println("hello");
                    String child_name = allEM_CH.get(k).getFullNameOfTheChild();
                    child_name_array[k] = child_name;


                    String ac_name = null;

                    if (k < allEM_AC.size()){

                        ac_name = allEM_AC.get(k).getCompanyName();
                        ac_name_array[k] = ac_name;

                    }





//                    testArray[0] = {name: adu};
//                    jsonObject.put("children", "adu");


                    if (k == 0) {

                        if (allEM_CH.size() == 1) {

                            json1 = json1.concat(openCloseQ).concat("subRows").concat(openCloseQ).concat(colon).concat(squareBO).concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(child_name).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat("").concat(openCloseQ).concat(closeCB).concat(squareBC);


//                            jsonObject.put("child",child_name);


                        } else {

//                                strResultEMCH = startLabel.concat(lableAC).concat(quote).concat(colon).concat(quote).concat(child_name).concat(middleLabel);

                            if (ac_name_array[k] != null ) {

                                json1 = json1.concat(openCloseQ).concat("subRows").concat(openCloseQ).concat(colon).concat(squareBO).concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(child_name).concat(openCloseQ).concat(comma)
                                        .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(ac_name).concat(openCloseQ).concat(closeCB).concat(comma);

                            } else {

                                json1 = json1.concat(openCloseQ).concat("subRows").concat(openCloseQ).concat(colon).concat(squareBO).concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(child_name).concat(openCloseQ).concat(comma)
                                        .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat("").concat(openCloseQ).concat(closeCB).concat(comma);

                            }


                        }


//                        strResultEMCH = startLabel.concat(lableCH).concat(quote).concat(colon).concat(quote).concat(child_name).concat(middleLabel);

                    } else if (k < t) {


//                        strResultEMCH = strResultEMCH.concat(lableCH).concat(quote).concat(colon).concat(quote).concat(child_name).concat(middleLabel);

                        if (ac_name_array[k] != null) {

                            json1 = json1.concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(child_name).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(ac_name).concat(openCloseQ).concat(closeCB).concat(comma);
                        }else {

                            json1 = json1.concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(child_name).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat("").concat(openCloseQ).concat(closeCB).concat(comma);

                        }


                    } else if (k == t) {


                        if (ac_name_array[k] != null) {

                            json1 = json1.concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(child_name).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(ac_name).concat(openCloseQ).concat(closeCB).concat(squareBC);
                        }else {

                            json1 = json1.concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(child_name).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat("").concat(openCloseQ).concat(closeCB).concat(squareBC);

                        }

                    }

//                    json1 = json1.concat(closeCB);
//                    em_Array[(int) j] = json1;


                }



            } else if (allEM_CH.size() < allEM_AC.size()) {



                String[] child_name_array = new String[allEM_AC.size()];
                String[] ac_name_array = new String[allEM_AC.size()];


                for (int k = 0; k < allEM_AC.size(); k++) {


                    System.out.println("hello");

                    String child_name = null;
                    if (k < allEM_CH.size()){

                        child_name = allEM_CH.get(k).getFullNameOfTheChild();
                        child_name_array[k] = child_name;

                    }



                    String ac_name = allEM_AC.get(k).getCompanyName();
                    ac_name_array[k] = ac_name;


//                    testArray[0] = {name: adu};
//                    jsonObject.put("children", "adu");


                    if (k == 0) {

                        if (allEM_AC.size() == 1) {

                            json1 = json1.concat(openCloseQ).concat("subRows").concat(openCloseQ).concat(colon).concat(squareBO).concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat("").concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(ac_name).concat(openCloseQ).concat(closeCB).concat(squareBC);


//                            jsonObject.put("child",child_name);


                        } else {

//                                strResultEMCH = startLabel.concat(lableAC).concat(quote).concat(colon).concat(quote).concat(child_name).concat(middleLabel);

                            if (child_name_array[k] != null) {

                                json1 = json1.concat(openCloseQ).concat("subRows").concat(openCloseQ).concat(colon).concat(squareBO).concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(child_name).concat(openCloseQ).concat(comma)
                                        .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(ac_name).concat(openCloseQ).concat(closeCB).concat(comma);

                            } else {

                                json1 = json1.concat(openCloseQ).concat("subRows").concat(openCloseQ).concat(colon).concat(squareBO).concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat("").concat(openCloseQ).concat(comma)
                                        .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(ac_name).concat(openCloseQ).concat(closeCB).concat(comma);

                            }


                        }


//                        strResultEMCH = startLabel.concat(lableCH).concat(quote).concat(colon).concat(quote).concat(child_name).concat(middleLabel);

                    } else if (k < l) {


//                        strResultEMCH = strResultEMCH.concat(lableCH).concat(quote).concat(colon).concat(quote).concat(child_name).concat(middleLabel);

                        if (child_name_array[k] != null) {

                            json1 = json1.concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(child_name).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(ac_name).concat(openCloseQ).concat(closeCB).concat(comma);
                        }else {

                            json1 = json1.concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat("").concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(ac_name).concat(openCloseQ).concat(closeCB).concat(comma);

                        }


                    } else if (k == l) {


                        if (child_name_array[k] != null) {

                            json1 = json1.concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(child_name).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(ac_name).concat(openCloseQ).concat(closeCB).concat(squareBC);
                        }else {

                            json1 = json1.concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat("").concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(ac_name).concat(openCloseQ).concat(closeCB).concat(squareBC);

                        }

                    }

//                    json1 = json1.concat(closeCB);
//                    em_Array[(int) j] = json1;


                }


            }


//            if (!allEM_AC.isEmpty()) {
//
//                for (int r = 0; r < allEM_AC.size(); r++) {
//
//
//                    System.out.println("hi");
//                    String ac_name = allEM_AC.get(r).getCompanyName();
//                    ac_name_array[r] = ac_name;
//
////                    jsonObject.put("companies",ac_name);
//
//                    if (r == 0) {
//
//                        if(allEM_AC.size() == 1){
//
//                            json1 = json1.concat(comma).concat(openCloseQ).concat("subRows").concat(openCloseQ).concat(colon).concat(squareBO).concat(openCB).concat(openCloseQ).concat("name").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(ac_name).concat(openCloseQ).concat(closeCB).concat(squareBC);
////                            jsonObject.put("child",child_name);
//
//                        }else {
//
////                                strResultEMCH = startLabel.concat(lableAC).concat(quote).concat(colon).concat(quote).concat(child_name).concat(middleLabel);
//                            json1 = json1.concat(comma).concat(openCloseQ).concat("subRows").concat(openCloseQ).concat(colon).concat(squareBO).concat(openCB).concat(openCloseQ).concat("name").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(ac_name).concat(openCloseQ).concat(closeCB).concat(comma);
//
//
//
//                        }
//
//
////                        strResultEMCH = startLabel.concat(lableCH).concat(quote).concat(colon).concat(quote).concat(child_name).concat(middleLabel);
//
//                    } else if (r < l) {
//
//
////                        strResultEMCH = strResultEMCH.concat(lableCH).concat(quote).concat(colon).concat(quote).concat(child_name).concat(middleLabel);
//                        json1 = json1.concat(openCB).concat(openCloseQ).concat("name").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(ac_name).concat(openCloseQ).concat(closeCB).concat(comma);
//
//                    } else if (r == l) {
//
//                        json1 = json1.concat(openCB).concat(openCloseQ).concat("name").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(ac_name).concat(openCloseQ).concat(closeCB).concat(squareBC);
//
//                    }
//
//
//
//
//                }
//
//            json1 = json1.concat(closeCB);
//            em_Array[(int)j] = json1;
//
//            }

            /******************/


            json1 = json1.concat(closeCB);
            em_Array[(int) j] = json1;

        }


        emArray_str = Arrays.toString(em_Array);






        return ResponseEntity.status(HttpStatus.OK)
                .body(emArray_str);

    }





    @GetMapping("tsh_new")
    public ResponseEntity<?> TSHFullDetailsNew() throws JsonProcessingException, JSONException {

        long i = topShareHoldersRepo.count();
        List<TopShareHolders> allEM= topShareHoldersRepo.findAll();
        String[] em_Array = new String[(int)i];




        for (long j = 0; j < i; j++) {


            TopShareHolders em = allEM.get((int) j);

            if (em.getNumberOfChildren() == 0 && em.getNumberOfAC() == 0){


                this.json2 = openCB.concat(openCloseQ).concat("id").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(em.getId().toString()).concat(openCloseQ).concat(comma)
                        .concat(openCloseQ).concat("nameOfShareHolder").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(em.getNameOfShareHolder()).concat(openCloseQ).concat(comma)
                        .concat(openCloseQ).concat("nameOfSpouse").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(em.getNameOfSpouse()).concat(openCloseQ).concat(comma)
                        .concat(openCloseQ).concat("shareAmountOfTheSpouse").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(em.getShareAmountOfTheSpouse())).concat(openCloseQ).concat(comma)
                        .concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(em.getNumberOfChildren())).concat(openCloseQ).concat(comma)
                        .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(em.getNumberOfAC())).concat(openCloseQ).concat(comma)
                        .concat(openCloseQ).concat("position").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(em.getPosition())).concat(openCloseQ);


            } else {

                this.json2 = openCB.concat(openCloseQ).concat("id").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(em.getId().toString()).concat(openCloseQ).concat(comma)
                        .concat(openCloseQ).concat("nameOfShareHolder").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(em.getNameOfShareHolder()).concat(openCloseQ).concat(comma)
                        .concat(openCloseQ).concat("nameOfSpouse").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(em.getNameOfSpouse()).concat(openCloseQ).concat(comma)
                        .concat(openCloseQ).concat("shareAmountOfTheSpouse").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(em.getShareAmountOfTheSpouse())).concat(openCloseQ).concat(comma)
                        .concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(em.getNumberOfChildren())).concat(openCloseQ).concat(comma)
                        .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(em.getNumberOfAC())).concat(openCloseQ).concat(comma)
                        .concat(openCloseQ).concat("position").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(em.getPosition())).concat(openCloseQ).concat(comma);

            }







            Long emID = em.getId();
            String emPos = em.getPosition();


            List<Children> allEM_CH = childrenRepo.findByParentsIdAndParentsPosition(emID, emPos);
//            System.out.println(allEM_CH);

            List<AffiliatedCompany> allEM_AC = affiliatedCompanyRepo.findByAffiliateIdAndAffiliatePosition(emID, emPos);
//            System.out.println(allEM_AC);


            int l = allEM_AC.size() - 1;
            int t = allEM_CH.size() - 1;


            if(allEM_AC.size() == allEM_CH.size()) {

                String[] child_name_array = new String[allEM_CH.size()];
                String[] ac_name_array = new String[allEM_AC.size()];


                for (int k = 0; k < allEM_CH.size(); k++) {


//                    System.out.println("hello");
                    String child_name = allEM_CH.get(k).getFullNameOfTheChild();
                    child_name_array[k] = child_name;

                    String ac_name = allEM_AC.get(k).getCompanyName();
                    ac_name_array[k] = ac_name;


                    if (k == 0) {

                        if (allEM_CH.size() == 1) {

                            json2 = json2.concat(openCloseQ).concat("subRows").concat(openCloseQ).concat(colon).concat(squareBO).concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(child_name).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(ac_name).concat(openCloseQ).concat(closeCB).concat(squareBC);


                        } else {

                            json2 = json2.concat(openCloseQ).concat("subRows").concat(openCloseQ).concat(colon).concat(squareBO).concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(child_name).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(ac_name).concat(openCloseQ).concat(closeCB).concat(comma);


                        }


                    } else if (k < t) {

                        json2 = json2.concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(child_name).concat(openCloseQ).concat(comma)
                                .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(ac_name).concat(openCloseQ).concat(closeCB).concat(comma);


                    } else if (k == t) {

                        json2 = json2.concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(child_name).concat(openCloseQ).concat(comma)
                                .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(ac_name).concat(openCloseQ).concat(closeCB).concat(squareBC);

                    }

                }


            } else if (allEM_CH.size() > allEM_AC.size()) {

                String[] child_name_array = new String[allEM_CH.size()];
                String[] ac_name_array = new String[allEM_CH.size()];


                for (int k = 0; k < allEM_CH.size(); k++) {


                    System.out.println("hello");
                    String child_name = allEM_CH.get(k).getFullNameOfTheChild();
                    child_name_array[k] = child_name;


                    String ac_name = null;

                    if (k < allEM_AC.size()){

                        ac_name = allEM_AC.get(k).getCompanyName();
                        ac_name_array[k] = ac_name;

                    }


                    if (k == 0) {

                        if (allEM_CH.size() == 1) {

                            json2 = json2.concat(openCloseQ).concat("subRows").concat(openCloseQ).concat(colon).concat(squareBO).concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(child_name).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat("").concat(openCloseQ).concat(closeCB).concat(squareBC);

                        } else {

                            if (ac_name_array[k] != null ) {

                                json2 = json2.concat(openCloseQ).concat("subRows").concat(openCloseQ).concat(colon).concat(squareBO).concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(child_name).concat(openCloseQ).concat(comma)
                                        .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(ac_name).concat(openCloseQ).concat(closeCB).concat(comma);

                            } else {

                                json2 = json2.concat(openCloseQ).concat("subRows").concat(openCloseQ).concat(colon).concat(squareBO).concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(child_name).concat(openCloseQ).concat(comma)
                                        .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat("").concat(openCloseQ).concat(closeCB).concat(comma);

                            }


                        }

                    } else if (k < t) {

                        if (ac_name_array[k] != null) {

                            json2 = json2.concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(child_name).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(ac_name).concat(openCloseQ).concat(closeCB).concat(comma);
                        }else {

                            json2 = json2.concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(child_name).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat("").concat(openCloseQ).concat(closeCB).concat(comma);

                        }


                    } else if (k == t) {


                        if (ac_name_array[k] != null) {

                            json2 = json2.concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(child_name).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(ac_name).concat(openCloseQ).concat(closeCB).concat(squareBC);
                        }else {

                            json2 = json2.concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(child_name).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat("").concat(openCloseQ).concat(closeCB).concat(squareBC);

                        }

                    }

                }



            } else if (allEM_CH.size() < allEM_AC.size()) {



                String[] child_name_array = new String[allEM_AC.size()];
                String[] ac_name_array = new String[allEM_AC.size()];


                for (int k = 0; k < allEM_AC.size(); k++) {


                    System.out.println("hello");

                    String child_name = null;
                    if (k < allEM_CH.size()){

                        child_name = allEM_CH.get(k).getFullNameOfTheChild();
                        child_name_array[k] = child_name;

                    }



                    String ac_name = allEM_AC.get(k).getCompanyName();
                    ac_name_array[k] = ac_name;

                    if (k == 0) {

                        if (allEM_AC.size() == 1) {

                            json2 = json2.concat(openCloseQ).concat("subRows").concat(openCloseQ).concat(colon).concat(squareBO).concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat("").concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(ac_name).concat(openCloseQ).concat(closeCB).concat(squareBC);

                        } else {

                            if (child_name_array[k] != null) {

                                json2 = json2.concat(openCloseQ).concat("subRows").concat(openCloseQ).concat(colon).concat(squareBO).concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(child_name).concat(openCloseQ).concat(comma)
                                        .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(ac_name).concat(openCloseQ).concat(closeCB).concat(comma);

                            } else {

                                json2 = json2.concat(openCloseQ).concat("subRows").concat(openCloseQ).concat(colon).concat(squareBO).concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat("").concat(openCloseQ).concat(comma)
                                        .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(ac_name).concat(openCloseQ).concat(closeCB).concat(comma);

                            }


                        }

                    } else if (k < l) {

                        if (child_name_array[k] != null) {

                            json2 = json2.concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(child_name).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(ac_name).concat(openCloseQ).concat(closeCB).concat(comma);
                        }else {

                            json2 = json2.concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat("").concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(ac_name).concat(openCloseQ).concat(closeCB).concat(comma);

                        }


                    } else if (k == l) {


                        if (child_name_array[k] != null) {

                            json2 = json2.concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(child_name).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(ac_name).concat(openCloseQ).concat(closeCB).concat(squareBC);
                        }else {

                            json2 = json2.concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat("").concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(ac_name).concat(openCloseQ).concat(closeCB).concat(squareBC);

                        }

                    }

                }


            }
            json2 = json2.concat(closeCB);
            em_Array[(int) j] = json2;

        }

        emArray_str = Arrays.toString(em_Array);

        return ResponseEntity.status(HttpStatus.OK)
                .body(emArray_str);

    }


    /***********************/




    @GetMapping("tsh_new_share")
    public ResponseEntity<?> TSHFullDetailsNewShareAmmount() throws JsonProcessingException, JSONException {

        long i = topShareHoldersRepo.count();
        List<TopShareHolders> allEM= topShareHoldersRepo.findAll();
        String[] em_Array = new String[(int)i];




        for (long j = 0; j < i; j++) {


            TopShareHolders em = allEM.get((int) j);



            if (em.getNumberOfChildren() == 0 && em.getNumberOfAC() == 0){


                this.json3 = openCB.concat(openCloseQ).concat("id").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(em.getId().toString()).concat(openCloseQ).concat(comma)
                        .concat(openCloseQ).concat("nameOfShareHolder").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(em.getNameOfShareHolder()).concat(openCloseQ).concat(comma)
                        .concat(openCloseQ).concat("nameOfSpouse").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(em.getNameOfSpouse()).concat(openCloseQ).concat(comma)
                        .concat(openCloseQ).concat("shareAmountOfTheSpouse").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(em.getShareAmountOfTheSpouse())).concat(openCloseQ).concat(comma)
                        .concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(em.getNumberOfChildren())).concat(openCloseQ).concat(comma)
                        .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(em.getNumberOfAC())).concat(openCloseQ).concat(comma)
                        .concat(openCloseQ).concat("position").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(em.getPosition())).concat(openCloseQ);


            } else {

                this.json3 = openCB.concat(openCloseQ).concat("id").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(em.getId().toString()).concat(openCloseQ).concat(comma)
                        .concat(openCloseQ).concat("nameOfShareHolder").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(em.getNameOfShareHolder()).concat(openCloseQ).concat(comma)
                        .concat(openCloseQ).concat("nameOfSpouse").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(em.getNameOfSpouse()).concat(openCloseQ).concat(comma)
                        .concat(openCloseQ).concat("shareAmountOfTheSpouse").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(em.getShareAmountOfTheSpouse())).concat(openCloseQ).concat(comma)
                        .concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(em.getNumberOfChildren())).concat(openCloseQ).concat(comma)
                        .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(em.getNumberOfAC())).concat(openCloseQ).concat(comma)
                        .concat(openCloseQ).concat("position").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(em.getPosition())).concat(openCloseQ).concat(comma);

            }







            Long emID = em.getId();
            String emPos = em.getPosition();


            List<Children> allEM_CH = childrenRepo.findByParentsIdAndParentsPosition(emID, emPos);
//            System.out.println(allEM_CH);

            List<AffiliatedCompany> allEM_AC = affiliatedCompanyRepo.findByAffiliateIdAndAffiliatePosition(emID, emPos);
//            System.out.println(allEM_AC);


            int l = allEM_AC.size() - 1;
            int t = allEM_CH.size() - 1;


            if(allEM_AC.size() == allEM_CH.size()) {

                String[] child_name_array = new String[allEM_CH.size()];
                String[] ac_name_array = new String[allEM_AC.size()];


                for (int k = 0; k < allEM_CH.size(); k++) {


//                    System.out.println("hello");
                    String child_name = allEM_CH.get(k).getFullNameOfTheChild();
                    child_name_array[k] = child_name;

                    double child_share_amount = allEM_CH.get(k).getShareAmount();
                    double company_share_amount = allEM_AC.get(k).getShareAmount();

                    String ac_name = allEM_AC.get(k).getCompanyName();
                    ac_name_array[k] = ac_name;


                    if (k == 0) {

                        if (allEM_CH.size() == 1) {

                            json3 = json3.concat(openCloseQ).concat("subRows").concat(openCloseQ).concat(colon).concat(squareBO).concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(child_name).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("childShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(child_share_amount)).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(ac_name).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("companyShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(company_share_amount)).concat(openCloseQ).concat(closeCB).concat(squareBC);


                        } else {

                            json3 = json3.concat(openCloseQ).concat("subRows").concat(openCloseQ).concat(colon).concat(squareBO).concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(child_name).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("childShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(child_share_amount)).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(ac_name).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("companyShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(company_share_amount)).concat(openCloseQ).concat(closeCB).concat(comma);


                        }


                    } else if (k < t) {

                        json3 = json3.concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(child_name).concat(openCloseQ).concat(comma)
                                .concat(openCloseQ).concat("childShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(child_share_amount)).concat(openCloseQ).concat(comma)
                                .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(ac_name).concat(openCloseQ).concat(comma)
                                .concat(openCloseQ).concat("companyShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(company_share_amount)).concat(openCloseQ).concat(closeCB).concat(comma);


                    } else if (k == t) {

                        json3 = json3.concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(child_name).concat(openCloseQ).concat(comma)
                                .concat(openCloseQ).concat("childShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(child_share_amount)).concat(openCloseQ).concat(comma)
                                .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(ac_name).concat(openCloseQ).concat(comma)
                                .concat(openCloseQ).concat("companyShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(company_share_amount)).concat(openCloseQ).concat(closeCB).concat(squareBC);

                    }

                }


            } else if (allEM_CH.size() > allEM_AC.size()) {

                String[] child_name_array = new String[allEM_CH.size()];
                String[] ac_name_array = new String[allEM_CH.size()];


                for (int k = 0; k < allEM_CH.size(); k++) {


                    System.out.println("hello");
                    String child_name = allEM_CH.get(k).getFullNameOfTheChild();
                    child_name_array[k] = child_name;


                    double child_share_amount = allEM_CH.get(k).getShareAmount();
                    double company_share_amount = 0;


                    String ac_name = null;

                    if (k < allEM_AC.size()){

                        ac_name = allEM_AC.get(k).getCompanyName();
                        ac_name_array[k] = ac_name;

                        company_share_amount = allEM_AC.get(k).getShareAmount();

                    }


                    if (k == 0) {

                        if (allEM_CH.size() == 1) {

                            json3 = json3.concat(openCloseQ).concat("subRows").concat(openCloseQ).concat(colon).concat(squareBO).concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(child_name).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("childShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(child_share_amount)).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat("").concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("companyShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(company_share_amount)).concat(openCloseQ).concat(closeCB).concat(squareBC);

                        } else {

                            if (ac_name_array[k] != null ) {

                                json3 = json3.concat(openCloseQ).concat("subRows").concat(openCloseQ).concat(colon).concat(squareBO).concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(child_name).concat(openCloseQ).concat(comma)
                                        .concat(openCloseQ).concat("childShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(child_share_amount)).concat(openCloseQ).concat(comma)
                                        .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(ac_name).concat(openCloseQ).concat(comma)
                                        .concat(openCloseQ).concat("companyShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(company_share_amount)).concat(openCloseQ).concat(closeCB).concat(comma);

                            } else {

                                json3 = json3.concat(openCloseQ).concat("subRows").concat(openCloseQ).concat(colon).concat(squareBO).concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(child_name).concat(openCloseQ).concat(comma)
                                        .concat(openCloseQ).concat("childShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(child_share_amount)).concat(openCloseQ).concat(comma)
                                        .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat("").concat(openCloseQ).concat(comma)
                                        .concat(openCloseQ).concat("companyShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(company_share_amount)).concat(openCloseQ).concat(closeCB).concat(comma);

                            }


                        }

                    } else if (k < t) {

                        if (ac_name_array[k] != null) {

                            json3 = json3.concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(child_name).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("childShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(child_share_amount)).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(ac_name).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("companyShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(company_share_amount)).concat(openCloseQ).concat(closeCB).concat(comma);
                        }else {

                            json3 = json3.concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(child_name).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("childShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(child_share_amount)).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat("").concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("companyShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(company_share_amount)).concat(openCloseQ).concat(closeCB).concat(comma);

                        }


                    } else if (k == t) {


                        if (ac_name_array[k] != null) {

                            json3 = json3.concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(child_name).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("childShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(child_share_amount)).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(ac_name).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("companyShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(company_share_amount)).concat(openCloseQ).concat(closeCB).concat(squareBC);
                        }else {

                            json3 = json3.concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(child_name).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("childShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(child_share_amount)).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat("").concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("companyShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(company_share_amount)).concat(openCloseQ).concat(closeCB).concat(squareBC);

                        }

                    }

                }



            } else if (allEM_CH.size() < allEM_AC.size()) {



                String[] child_name_array = new String[allEM_AC.size()];
                String[] ac_name_array = new String[allEM_AC.size()];


                for (int k = 0; k < allEM_AC.size(); k++) {


                    System.out.println("hello");

                    double child_share_amount = 0;
                    double company_share_amount = allEM_AC.get(k).getShareAmount();

                    String child_name = null;
                    if (k < allEM_CH.size()){

                        child_name = allEM_CH.get(k).getFullNameOfTheChild();
                        child_name_array[k] = child_name;

                        child_share_amount = allEM_CH.get(k).getShareAmount();

                    }



                    String ac_name = allEM_AC.get(k).getCompanyName();
                    ac_name_array[k] = ac_name;

                    if (k == 0) {

                        if (allEM_AC.size() == 1) {

                            json3 = json3.concat(openCloseQ).concat("subRows").concat(openCloseQ).concat(colon).concat(squareBO).concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat("").concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("childShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(child_share_amount)).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(ac_name).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("companyShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(company_share_amount)).concat(openCloseQ).concat(closeCB).concat(squareBC);

                        } else {

                            if (child_name_array[k] != null) {

                                json3 = json3.concat(openCloseQ).concat("subRows").concat(openCloseQ).concat(colon).concat(squareBO).concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(child_name).concat(openCloseQ).concat(comma)
                                        .concat(openCloseQ).concat("childShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(child_share_amount)).concat(openCloseQ).concat(comma)
                                        .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(ac_name).concat(openCloseQ).concat(comma)
                                        .concat(openCloseQ).concat("companyShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(company_share_amount)).concat(openCloseQ).concat(closeCB).concat(comma);

                            } else {

                                json3 = json3.concat(openCloseQ).concat("subRows").concat(openCloseQ).concat(colon).concat(squareBO).concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat("").concat(openCloseQ).concat(comma)
                                        .concat(openCloseQ).concat("childShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(child_share_amount)).concat(openCloseQ).concat(comma)
                                        .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(ac_name).concat(openCloseQ).concat(comma)
                                        .concat(openCloseQ).concat("companyShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(company_share_amount)).concat(openCloseQ).concat(closeCB).concat(comma);

                            }


                        }

                    } else if (k < l) {

                        if (child_name_array[k] != null) {

                            json3 = json3.concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(child_name).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("childShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(child_share_amount)).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(ac_name).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("companyShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(company_share_amount)).concat(openCloseQ).concat(closeCB).concat(comma);
                        }else {

                            json3 = json3.concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat("").concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("childShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(child_share_amount)).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(ac_name).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("companyShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(company_share_amount)).concat(openCloseQ).concat(closeCB).concat(comma);

                        }


                    } else if (k == l) {


                        if (child_name_array[k] != null) {

                            json3 = json3.concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(child_name).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("childShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(child_share_amount)).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(ac_name).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("companyShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(company_share_amount)).concat(openCloseQ).concat(closeCB).concat(squareBC);
                        }else {

                            json3 = json3.concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat("").concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("childShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(child_share_amount)).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(ac_name).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("companyShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(company_share_amount)).concat(openCloseQ).concat(closeCB).concat(squareBC);

                        }

                    }

                }


            }
            json3 = json3.concat(closeCB);
            em_Array[(int) j] = json3;

        }

        emArray_str = Arrays.toString(em_Array);

        return ResponseEntity.status(HttpStatus.OK)
                .body(emArray_str);

    }




    @GetMapping("tsh_new_Zero_Case")
    public ResponseEntity<?> TSHFullDetailsNewShareAmountZeroCase() throws JsonProcessingException, JSONException {

        long i = topShareHoldersRepo.count();
        List<TopShareHolders> allEM= topShareHoldersRepo.findAll();
        String[] em_Array = new String[(int)i];




        for (long j = 0; j < i; j++) {


            TopShareHolders em = allEM.get((int) j);



            if (em.getNumberOfChildren() == 0 && em.getNumberOfAC() == 0){


                this.json4 = openCB.concat(openCloseQ).concat("id").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(em.getId().toString()).concat(openCloseQ).concat(comma)
                        .concat(openCloseQ).concat("nameOfShareHolder").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(em.getNameOfShareHolder()).concat(openCloseQ).concat(comma)
                        .concat(openCloseQ).concat("shareAmountOfTheHolder").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(em.getShareAmountOfTheHolder())).concat(openCloseQ).concat(comma)
                        .concat(openCloseQ).concat("nameOfSpouse").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(em.getNameOfSpouse()).concat(openCloseQ).concat(comma)
                        .concat(openCloseQ).concat("shareAmountOfTheSpouse").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(em.getShareAmountOfTheSpouse())).concat(openCloseQ).concat(comma)
                        .concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(em.getNumberOfChildren())).concat(openCloseQ).concat(comma)
                        .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(em.getNumberOfAC())).concat(openCloseQ).concat(comma)
                        .concat(openCloseQ).concat("position").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(em.getPosition())).concat(openCloseQ).concat(comma).concat(openCloseQ)
                        .concat("subRows").concat(openCloseQ).concat(colon).concat(squareBO).concat(openCB)
                        .concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(0)).concat(openCloseQ).concat(comma)
                        .concat(openCloseQ).concat("childShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(0)).concat(openCloseQ).concat(comma)
                        .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(0)).concat(openCloseQ).concat(comma)
                        .concat(openCloseQ).concat("companyShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(0)).concat(openCloseQ)
                        .concat(closeCB).concat(squareBC);




            } else {

                this.json4 = openCB.concat(openCloseQ).concat("id").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(em.getId().toString()).concat(openCloseQ).concat(comma)
                        .concat(openCloseQ).concat("nameOfShareHolder").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(em.getNameOfShareHolder()).concat(openCloseQ).concat(comma)
                        .concat(openCloseQ).concat("shareAmountOfTheHolder").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(em.getShareAmountOfTheHolder())).concat(openCloseQ).concat(comma)
                        .concat(openCloseQ).concat("nameOfSpouse").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(em.getNameOfSpouse()).concat(openCloseQ).concat(comma)
                        .concat(openCloseQ).concat("shareAmountOfTheSpouse").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(em.getShareAmountOfTheSpouse())).concat(openCloseQ).concat(comma)
                        .concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(em.getNumberOfChildren())).concat(openCloseQ).concat(comma)
                        .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(em.getNumberOfAC())).concat(openCloseQ).concat(comma)
                        .concat(openCloseQ).concat("position").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(em.getPosition())).concat(openCloseQ).concat(comma);

            }







            Long emID = em.getId();
            String emPos = em.getPosition();


            List<Children> allEM_CH = childrenRepo.findByParentsIdAndParentsPosition(emID, emPos);
//            System.out.println(allEM_CH);

            List<AffiliatedCompany> allEM_AC = affiliatedCompanyRepo.findByAffiliateIdAndAffiliatePosition(emID, emPos);
//            System.out.println(allEM_AC);


            int l = allEM_AC.size() - 1;
            int t = allEM_CH.size() - 1;


            if(allEM_AC.size() == allEM_CH.size()) {

                String[] child_name_array = new String[allEM_CH.size()];
                String[] ac_name_array = new String[allEM_AC.size()];


                for (int k = 0; k < allEM_CH.size(); k++) {


//                    System.out.println("hello");
                    String child_name = allEM_CH.get(k).getFullNameOfTheChild();
                    child_name_array[k] = child_name;

                    double child_share_amount = allEM_CH.get(k).getShareAmount();
                    double company_share_amount = allEM_AC.get(k).getShareAmount();

                    String ac_name = allEM_AC.get(k).getCompanyName();
                    ac_name_array[k] = ac_name;


                    if (k == 0) {

                        if (allEM_CH.size() == 1) {

                            json4 = json4.concat(openCloseQ).concat("subRows").concat(openCloseQ).concat(colon).concat(squareBO).concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(child_name).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("childShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(child_share_amount)).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(ac_name).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("companyShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(company_share_amount)).concat(openCloseQ).concat(closeCB).concat(squareBC);


                        } else {

                            json4 = json4.concat(openCloseQ).concat("subRows").concat(openCloseQ).concat(colon).concat(squareBO).concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(child_name).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("childShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(child_share_amount)).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(ac_name).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("companyShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(company_share_amount)).concat(openCloseQ).concat(closeCB).concat(comma);


                        }


                    } else if (k < t) {

                        json4 = json4.concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(child_name).concat(openCloseQ).concat(comma)
                                .concat(openCloseQ).concat("childShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(child_share_amount)).concat(openCloseQ).concat(comma)
                                .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(ac_name).concat(openCloseQ).concat(comma)
                                .concat(openCloseQ).concat("companyShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(company_share_amount)).concat(openCloseQ).concat(closeCB).concat(comma);


                    } else if (k == t) {

                        json4 = json4.concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(child_name).concat(openCloseQ).concat(comma)
                                .concat(openCloseQ).concat("childShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(child_share_amount)).concat(openCloseQ).concat(comma)
                                .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(ac_name).concat(openCloseQ).concat(comma)
                                .concat(openCloseQ).concat("companyShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(company_share_amount)).concat(openCloseQ).concat(closeCB).concat(squareBC);

                    }

                }


            } else if (allEM_CH.size() > allEM_AC.size()) {

                String[] child_name_array = new String[allEM_CH.size()];
                String[] ac_name_array = new String[allEM_CH.size()];


                for (int k = 0; k < allEM_CH.size(); k++) {


                    System.out.println("hello");
                    String child_name = allEM_CH.get(k).getFullNameOfTheChild();
                    child_name_array[k] = child_name;


                    double child_share_amount = allEM_CH.get(k).getShareAmount();
                    double company_share_amount = 0;


                    String ac_name = null;

                    if (k < allEM_AC.size()){

                        ac_name = allEM_AC.get(k).getCompanyName();
                        ac_name_array[k] = ac_name;

                        company_share_amount = allEM_AC.get(k).getShareAmount();

                    }


                    if (k == 0) {

                        if (allEM_CH.size() == 1) {

                            json4 = json4.concat(openCloseQ).concat("subRows").concat(openCloseQ).concat(colon).concat(squareBO).concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(child_name).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("childShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(child_share_amount)).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat("").concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("companyShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(company_share_amount)).concat(openCloseQ).concat(closeCB).concat(squareBC);

                        } else {

                            if (ac_name_array[k] != null ) {

                                json4 = json4.concat(openCloseQ).concat("subRows").concat(openCloseQ).concat(colon).concat(squareBO).concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(child_name).concat(openCloseQ).concat(comma)
                                        .concat(openCloseQ).concat("childShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(child_share_amount)).concat(openCloseQ).concat(comma)
                                        .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(ac_name).concat(openCloseQ).concat(comma)
                                        .concat(openCloseQ).concat("companyShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(company_share_amount)).concat(openCloseQ).concat(closeCB).concat(comma);

                            } else {

                                json4 = json4.concat(openCloseQ).concat("subRows").concat(openCloseQ).concat(colon).concat(squareBO).concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(child_name).concat(openCloseQ).concat(comma)
                                        .concat(openCloseQ).concat("childShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(child_share_amount)).concat(openCloseQ).concat(comma)
                                        .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat("").concat(openCloseQ).concat(comma)
                                        .concat(openCloseQ).concat("companyShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(company_share_amount)).concat(openCloseQ).concat(closeCB).concat(comma);

                            }


                        }

                    } else if (k < t) {

                        if (ac_name_array[k] != null) {

                            json4 = json4.concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(child_name).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("childShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(child_share_amount)).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(ac_name).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("companyShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(company_share_amount)).concat(openCloseQ).concat(closeCB).concat(comma);
                        }else {

                            json4 = json4.concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(child_name).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("childShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(child_share_amount)).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat("").concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("companyShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(company_share_amount)).concat(openCloseQ).concat(closeCB).concat(comma);

                        }


                    } else if (k == t) {


                        if (ac_name_array[k] != null) {

                            json4 = json4.concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(child_name).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("childShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(child_share_amount)).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(ac_name).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("companyShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(company_share_amount)).concat(openCloseQ).concat(closeCB).concat(squareBC);
                        }else {

                            json4 = json4.concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(child_name).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("childShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(child_share_amount)).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat("").concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("companyShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(company_share_amount)).concat(openCloseQ).concat(closeCB).concat(squareBC);

                        }

                    }

                }



            } else if (allEM_CH.size() < allEM_AC.size()) {



                String[] child_name_array = new String[allEM_AC.size()];
                String[] ac_name_array = new String[allEM_AC.size()];


                for (int k = 0; k < allEM_AC.size(); k++) {


                    System.out.println("hello");

                    double child_share_amount = 0;
                    double company_share_amount = allEM_AC.get(k).getShareAmount();

                    String child_name = null;
                    if (k < allEM_CH.size()){

                        child_name = allEM_CH.get(k).getFullNameOfTheChild();
                        child_name_array[k] = child_name;

                        child_share_amount = allEM_CH.get(k).getShareAmount();

                    }



                    String ac_name = allEM_AC.get(k).getCompanyName();
                    ac_name_array[k] = ac_name;

                    if (k == 0) {

                        if (allEM_AC.size() == 1) {

                            json4 = json4.concat(openCloseQ).concat("subRows").concat(openCloseQ).concat(colon).concat(squareBO).concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat("").concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("childShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(child_share_amount)).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(ac_name).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("companyShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(company_share_amount)).concat(openCloseQ).concat(closeCB).concat(squareBC);

                        } else {

                            if (child_name_array[k] != null) {

                                json4 = json4.concat(openCloseQ).concat("subRows").concat(openCloseQ).concat(colon).concat(squareBO).concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(child_name).concat(openCloseQ).concat(comma)
                                        .concat(openCloseQ).concat("childShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(child_share_amount)).concat(openCloseQ).concat(comma)
                                        .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(ac_name).concat(openCloseQ).concat(comma)
                                        .concat(openCloseQ).concat("companyShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(company_share_amount)).concat(openCloseQ).concat(closeCB).concat(comma);

                            } else {

                                json4 = json4.concat(openCloseQ).concat("subRows").concat(openCloseQ).concat(colon).concat(squareBO).concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat("").concat(openCloseQ).concat(comma)
                                        .concat(openCloseQ).concat("childShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(child_share_amount)).concat(openCloseQ).concat(comma)
                                        .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(ac_name).concat(openCloseQ).concat(comma)
                                        .concat(openCloseQ).concat("companyShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(company_share_amount)).concat(openCloseQ).concat(closeCB).concat(comma);

                            }


                        }

                    } else if (k < l) {

                        if (child_name_array[k] != null) {

                            json4 = json4.concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(child_name).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("childShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(child_share_amount)).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(ac_name).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("companyShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(company_share_amount)).concat(openCloseQ).concat(closeCB).concat(comma);
                        }else {

                            json4 = json4.concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat("").concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("childShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(child_share_amount)).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(ac_name).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("companyShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(company_share_amount)).concat(openCloseQ).concat(closeCB).concat(comma);

                        }


                    } else if (k == l) {


                        if (child_name_array[k] != null) {

                            json4 = json4.concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(child_name).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("childShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(child_share_amount)).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(ac_name).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("companyShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(company_share_amount)).concat(openCloseQ).concat(closeCB).concat(squareBC);
                        }else {

                            json4 = json4.concat(openCB).concat(openCloseQ).concat("child").concat(openCloseQ).concat(colon).concat(openCloseQ).concat("").concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("childShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(child_share_amount)).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("company").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(ac_name).concat(openCloseQ).concat(comma)
                                    .concat(openCloseQ).concat("companyShareAmount").concat(openCloseQ).concat(colon).concat(openCloseQ).concat(String.valueOf(company_share_amount)).concat(openCloseQ).concat(closeCB).concat(squareBC);

                        }

                    }

                }


            }
            json4 = json4.concat(closeCB);
            em_Array[(int) j] = json4;

        }

        emArray_str = Arrays.toString(em_Array);

        return ResponseEntity.status(HttpStatus.OK)
                .body(emArray_str);

    }



}
