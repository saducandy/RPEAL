package com.example.rpeal.Repository;

import com.example.rpeal.Model.ACHistory;
import com.example.rpeal.Model.AffiliatedCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ACHRepo extends JpaRepository<ACHistory, Long> {

    List<ACHistory> findByAffiliateIdAndAffiliateName(Long id, String name);
    List<ACHistory> findByAffiliateNameAndAffiliatePosition(String name, String position);

    List<ACHistory> findByAffiliateIdAndAffiliatePosition(Long id, String position);


}
