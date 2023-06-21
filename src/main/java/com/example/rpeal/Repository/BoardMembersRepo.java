package com.example.rpeal.Repository;

import com.example.rpeal.Model.BoardMembers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardMembersRepo extends JpaRepository<BoardMembers, Long> {
}
