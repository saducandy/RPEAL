package com.example.rpeal.Repository;

import com.example.rpeal.Model.EMHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EMHRepo extends JpaRepository<EMHistory, Long> {

    EMHistory findByNameOfEmployeeAndCurrentPosition(String name, String position);

    List<EMHistory> findAllByUserId(Long id);

}
