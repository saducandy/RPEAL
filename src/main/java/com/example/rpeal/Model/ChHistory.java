package com.example.rpeal.Model;

import jakarta.persistence.*;

import java.time.ZonedDateTime;

@Entity
public class ChHistory {

    /**fields**/



    private Long userId;
    private String fullNameOfTheChild;
    private String fullNameOfTheFather;
    private String parentsPosition;
    private Long parentsId;
    @Column(nullable = true)
    private double shareAmount;
    private String action;
    private ZonedDateTime date;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;




    /****************/

    public double getShareAmount() {
        return shareAmount;
    }

    public void setShareAmount(double shareAmount) {
        this.shareAmount = shareAmount;
    }

    /**Getters And Setters**/





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

    public String getFullNameOfTheChild() {
        return fullNameOfTheChild;
    }

    public void setFullNameOfTheChild(String fullNameOfTheChild) {
        this.fullNameOfTheChild = fullNameOfTheChild;
    }

    public String getFullNameOfTheFather() {
        return fullNameOfTheFather;
    }

    public void setFullNameOfTheFather(String fullNameOfTheFather) {
        this.fullNameOfTheFather = fullNameOfTheFather;
    }

    public String getParentsPosition() {
        return parentsPosition;
    }

    public void setParentsPosition(String parentsPosition) {
        this.parentsPosition = parentsPosition;
    }

    public Long getParentsId() {
        return parentsId;
    }

    public void setParentsId(Long parentsId) {
        this.parentsId = parentsId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long historyId) {
        this.id = historyId;
    }

    /***************************/

    /**
     * ToString
     **/
    @Override
    public String toString() {
        return "ChHistory{" +
                "id=" + userId +
                ", fullNameOfTheChild='" + fullNameOfTheChild + '\'' +
                ", fullNameOfTheFather='" + fullNameOfTheFather + '\'' +
                ", parentsPosition='" + parentsPosition + '\'' +
                ", parentsId=" + parentsId +
                ", shareAmount=" + shareAmount +
                ", action='" + action + '\'' +
                ", date=" + date +
                ", historyId=" + id +
                '}';
    }


    /*****************/


}
