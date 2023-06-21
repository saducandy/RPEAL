package com.example.rpeal.Repository;

import com.example.rpeal.Model.AffiliatedCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AffiliatedCompanyRepo extends JpaRepository<AffiliatedCompany, Long> {

    List<AffiliatedCompany> findByAffiliateIdAndAffiliateName(Long id, String name);

}
