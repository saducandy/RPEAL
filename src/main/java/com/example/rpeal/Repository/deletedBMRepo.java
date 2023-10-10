package com.example.rpeal.Repository;

import com.example.rpeal.Model.BMHistory;
import com.example.rpeal.Model.DeletedBM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface deletedBMRepo extends JpaRepository<DeletedBM, Long> {
}
