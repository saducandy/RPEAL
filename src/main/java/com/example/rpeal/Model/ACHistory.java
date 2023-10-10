package com.example.rpeal.Model;

import jakarta.persistence.*;

import java.time.ZonedDateTime;

@Entity
public class ACHistory {


    /**Fields**/



    private Long userId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String companyName;
    private String affiliateName;
    private long affiliateId;
    @Column(nullable = true)
    private double shareAmount;
    private String affiliatePosition;
    private String action;
    private ZonedDateTime date;





    /************/




    public String getAffiliatePosition() {
        return affiliatePosition;
    }

    public void setAffiliatePosition(String affiliatePosition) {
        this.affiliatePosition = affiliatePosition;
    }

    /**Getter And Setters**/


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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long id) {
        this.userId = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAffiliateName() {
        return affiliateName;
    }

    public void setAffiliateName(String affiliatePosition) {
        this.affiliateName = affiliatePosition;
    }

    public long getAffiliateId() {
        return affiliateId;
    }

    public void setAffiliateId(long affiliateId) {
        this.affiliateId = affiliateId;
    }

    public double getShareAmount() {
        return shareAmount;
    }

    public void setShareAmount(double shareAmount) {
        this.shareAmount = shareAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long historyId) {
        this.id = historyId;
    }

    /************************/

    /**
     * To string
     **/
    @Override
    public String toString() {
        return "ACHistory{" +
                "id=" + userId +
                ", historyId=" + id +
                ", companyName='" + companyName + '\'' +
                ", affiliateName='" + affiliateName + '\'' +
                ", affiliateId=" + affiliateId +
                ", shareAmount=" + shareAmount +
                ", affiliatePosition='" + affiliatePosition + '\'' +
                ", action='" + action + '\'' +
                ", date=" + date +
                '}';
    }


    /******************/




}
