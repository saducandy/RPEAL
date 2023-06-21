package com.example.rpeal.Model;

import jakarta.persistence.*;

@Entity
public class Children {


    /**fields**/

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fullNameOfTheChild;
    private String fullNameOfTheFather;
    private String parentsPosition;
    private Long parentsId;
    @Column(nullable = true)
    private double shareAmount;




    /****************/

    public double getShareAmount() {
        return shareAmount;
    }

    public void setShareAmount(double shareAmount) {
        this.shareAmount = shareAmount;
    }

    /**Getters And Setters**/


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


    /***************************/

    /**ToString**/
    @Override
    public String toString() {
        return "Children{" +
                "id=" + id +
                ", fullNameOfTheChild='" + fullNameOfTheChild + '\'' +
                ", fullNameOfTheFather='" + fullNameOfTheFather + '\'' +
                ", parentsPosition='" + parentsPosition + '\'' +
                ", parentsId=" + parentsId +
                '}';
    }


    /*****************/


}
