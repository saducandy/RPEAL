package com.example.rpeal.Repository;

import com.example.rpeal.Model.ExecutiveManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExecutiveManagementRepo extends JpaRepository<ExecutiveManagement, Long> {

    ExecutiveManagement findByNameOfEmployeeAndCurrentPosition(String name, String position);

}
