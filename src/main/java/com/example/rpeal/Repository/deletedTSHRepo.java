package com.example.rpeal.Repository;

import com.example.rpeal.Model.DeletedBM;
import com.example.rpeal.Model.DeletedTSH;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface deletedTSHRepo extends JpaRepository<DeletedTSH, Long> {
}
