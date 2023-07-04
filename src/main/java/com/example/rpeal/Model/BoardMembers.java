package com.example.rpeal.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class BoardMembers {


    /**fields**/

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nameOfTheBoardMember;
    private String relatedCompanies;
    private String relation;
//    private String position;


    /*************/


//    public String getPosition() {
//        return position;
//    }
//
//    public void setPosition(String position) {
//        this.position = position;
//    }

    /**Getters And Setters**/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameOfTheBoardMember() {
        return nameOfTheBoardMember;
    }

    public void setNameOfTheBoardMember(String nameOfTheBoardMember) {
        this.nameOfTheBoardMember = nameOfTheBoardMember;
    }

    public String getRelatedCompanies() {
        return relatedCompanies;
    }

    public void setRelatedCompanies(String relatedCompanies) {
        this.relatedCompanies = relatedCompanies;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }


    /**************************/

    /**
     * To String
     **/
    @Override
    public String toString() {
        return "BoardMembers{" +
                "id=" + id +
                ", nameOfTheBoardMember='" + nameOfTheBoardMember + '\'' +
                ", relatedCompanies='" + relatedCompanies + '\'' +
                ", relation='" + relation + '\'' +
                '}';
    }


    /*****************/


}
