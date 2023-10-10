package com.example.rpeal.Repository;

import com.example.rpeal.Model.ChHistory;
import com.example.rpeal.Model.Children;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChHRepo extends JpaRepository<ChHistory, Long> {

    List<ChHistory> findByFullNameOfTheFatherAndParentsId(String name, Long id);
    List<ChHistory> findByFullNameOfTheFatherAndParentsPosition(String name, String position);

    List<ChHistory> findByParentsIdAndParentsPosition(Long id, String position);

}

