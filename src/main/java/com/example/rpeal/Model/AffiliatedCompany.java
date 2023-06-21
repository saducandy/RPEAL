package com.example.rpeal.Model;

import jakarta.persistence.*;

@Entity
public class AffiliatedCompany {


    /**Fields**/

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String companyName;
    private String affiliateName;
    private long affiliateId;
    @Column(nullable = true)
    private double shareAmount;



    /************/

    /**Getter And Setters**/
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


    /************************/

    /**To string**/
    @Override
    public String toString() {
        return "AffiliatedCompany{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", affiliatePosition='" + affiliateName + '\'' +
                ", affiliateId='" + affiliateId + '\'' +
                ", shareAmount=" + shareAmount +
                '}';
    }


    /******************/


}
