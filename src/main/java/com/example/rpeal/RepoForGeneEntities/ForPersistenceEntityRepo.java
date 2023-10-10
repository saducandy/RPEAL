package com.example.rpeal.RepoForGeneEntities;

import com.example.rpeal.genetatedEntities.ForPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForPersistenceEntityRepo extends JpaRepository<ForPersistenceEntity, Integer> {
}
