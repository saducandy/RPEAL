package com.example.rpeal.Repository;

import com.example.rpeal.Model.Children;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChildrenRepo extends JpaRepository<Children, Long> {

    List<Children> findByFullNameOfTheFatherAndParentsId(String name, Long id);

}
