package com.example.rpeal.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.ZonedDateTime;

@Entity
public class TSHHistory {

    /**defining Fields**/




    private Long userId;
    private String nameOfShareHolder;
    private double shareAmountOfTheHolder;
    private String nameOfSpouse;
    private double shareAmountOfTheSpouse;
    private int numberOfChildren;
    private int numberOfAC;
    private String position;

    private String action;
    private ZonedDateTime date;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    /**********************/


    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getNumberOfAC() {
        return numberOfAC;
    }

    public void setNumberOfAC(int numberOfAC) {
        this.numberOfAC = numberOfAC;
    }

    /**Getter and Setters**/


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long id) {
        this.userId = id;
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

    public String getNameOfShareHolder() {
        return nameOfShareHolder;
    }

    public void setNameOfShareHolder(String nameOfShareHolder) {
        this.nameOfShareHolder = nameOfShareHolder;
    }

    public double getShareAmountOfTheHolder() {
        return shareAmountOfTheHolder;
    }

    public void setShareAmountOfTheHolder(double shareAmountOfTheHolder) {
        this.shareAmountOfTheHolder = shareAmountOfTheHolder;
    }

    public String getNameOfSpouse() {
        return nameOfSpouse;
    }

    public void setNameOfSpouse(String nameOfSpouse) {
        this.nameOfSpouse = nameOfSpouse;
    }

    public double getShareAmountOfTheSpouse() {
        return shareAmountOfTheSpouse;
    }

    public void setShareAmountOfTheSpouse(double shareAmountOfTheSpouse) {
        this.shareAmountOfTheSpouse = shareAmountOfTheSpouse;
    }

    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(int numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long historyId) {
        this.id = historyId;
    }

    /**************************/

    /**
     * ToString
     **/
    @Override
    public String toString() {
        return "TSHHistory{" +
                "id=" + userId +
                ", nameOfShareHolder='" + nameOfShareHolder + '\'' +
                ", shareAmountOfTheHolder=" + shareAmountOfTheHolder +
                ", nameOfSpouse='" + nameOfSpouse + '\'' +
                ", shareAmountOfTheSpouse=" + shareAmountOfTheSpouse +
                ", numberOfChildren=" + numberOfChildren +
                ", numberOfAC=" + numberOfAC +
                ", position='" + position + '\'' +
                ", action='" + action + '\'' +
                ", date=" + date +
                ", historyId=" + id +
                '}';
    }


    /******************/


}
