package com.example.rpeal.Repository;

import com.example.rpeal.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepo extends JpaRepository<Users, Long> {

    Users findByUserNameAndPassWord(String un, String pw);

    Users findByUserName(String un);

}
