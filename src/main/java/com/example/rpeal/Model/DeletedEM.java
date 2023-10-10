package com.example.rpeal.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.ZonedDateTime;

@Entity
public class DeletedEM {

    /**defining the fields**/





    private Long id;
    private String nameOfEmployee;
    private String currentPosition;
    private String nameOfSpouse;
    private int numberOfChildren;
    private int numberOfAC;
    private String action;
    private ZonedDateTime date;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long deletionId;



    /**************************/


    public int getNumberOfAC() {
        return numberOfAC;
    }

    public void setNumberOfAC(int numberOfAC) {
        this.numberOfAC = numberOfAC;
    }

    /**Getters and Setters**/


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getNameOfEmployee() {
        return nameOfEmployee;
    }

    public void setNameOfEmployee(String nameOfEmployee) {
        this.nameOfEmployee = nameOfEmployee;
    }

    public String getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(String currentPosition) {
        this.currentPosition = currentPosition;
    }

    public String getNameOfSpouse() {
        return nameOfSpouse;
    }

    public void setNameOfSpouse(String nameOfSpouse) {
        this.nameOfSpouse = nameOfSpouse;
    }

    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(int numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public Long getDeletionId() {
        return deletionId;
    }

    public void setDeletionId(Long historyId) {
        this.deletionId = historyId;
    }

    /**************************/


    /**
     * To String
     **/
    @Override
    public String toString() {
        return "EMHistory{" +
                "id=" + id +
                ", nameOfEmployee='" + nameOfEmployee + '\'' +
                ", currentPosition='" + currentPosition + '\'' +
                ", nameOfSpouse='" + nameOfSpouse + '\'' +
                ", numberOfChildren=" + numberOfChildren +
                ", numberOfAC=" + numberOfAC +
                ", action='" + action + '\'' +
                ", date=" + date +
                ", historyId=" + deletionId +
                '}';
    }
/****************/


}
