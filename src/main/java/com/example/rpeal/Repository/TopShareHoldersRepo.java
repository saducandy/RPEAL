package com.example.rpeal.Repository;

import com.example.rpeal.Model.TopShareHolders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopShareHoldersRepo extends JpaRepository<TopShareHolders, Long> {

    TopShareHolders findByNameOfShareHolder(String name);

}
