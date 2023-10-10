package com.example.rpeal.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.ZonedDateTime;

@Entity
public class BMHistory {

    /**fields**/



    private Long userId;
    private String nameOfTheBoardMember;
    private String relatedCompanies;
    private String relation;
    private String position;
    private String action;
    private ZonedDateTime date;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    /*************/


    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    /**Getters And Setters**/



    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long id) {
        this.userId = id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long historyId) {
        this.id = historyId;
    }

    /**************************/

    /**
     * To String
     **/
    @Override
    public String toString() {
        return "BMHistory{" +
                "id=" + userId +
                ", nameOfTheBoardMember='" + nameOfTheBoardMember + '\'' +
                ", relatedCompanies='" + relatedCompanies + '\'' +
                ", relation='" + relation + '\'' +
                ", position='" + position + '\'' +
                ", action='" + action + '\'' +
                ", date=" + date +
                ", historyId=" + id +
                '}';
    }


    /*****************/


}
