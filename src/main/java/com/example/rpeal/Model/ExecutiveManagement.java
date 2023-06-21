package com.example.rpeal.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ExecutiveManagement {


    /**defining the fields**/


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nameOfEmployee;
    private String currentPosition;
    private String nameOfSpouse;
    private int numberOfChildren;



    /**************************/


    /**Getters and Setters**/
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


    /**************************/


    /**To String**/
    @Override
    public String toString() {
        return "ExecutiveManagement{" +
                "id=" + id +
                ", nameOfEmployee='" + nameOfEmployee + '\'' +
                ", currentPosition='" + currentPosition + '\'' +
                ", nameOfSpouse='" + nameOfSpouse + '\'' +
                ", numberOfChildren=" + numberOfChildren +
                '}';
    }


    /*****************/


}
