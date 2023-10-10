package com.example.rpeal.Repository;

import com.example.rpeal.Model.DeletedBM;
import com.example.rpeal.Model.DeletedEM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface deletedEMRepo extends JpaRepository<DeletedEM, Long> {
}
