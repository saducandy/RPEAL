package com.example.rpeal.Repository;

import com.example.rpeal.Model.TSHHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TSHHRepo extends JpaRepository<TSHHistory, Long> {

    TSHHistory findByNameOfShareHolder(String name);

    List<TSHHistory> findAllByUserId(Long id);

}
