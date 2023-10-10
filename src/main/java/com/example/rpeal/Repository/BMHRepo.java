package com.example.rpeal.Repository;

import com.example.rpeal.Model.BMHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BMHRepo extends JpaRepository<BMHistory, Long> {

    List<BMHistory> findAllByUserId(Long id);

}
