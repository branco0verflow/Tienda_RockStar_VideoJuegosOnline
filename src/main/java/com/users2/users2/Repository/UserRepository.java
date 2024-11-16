package com.users2.users2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.users2.users2.Entity.UserEntity;
import java.util.List;


public interface UserRepository extends JpaRepository<UserEntity, Integer>{
    List<UserEntity> findByEmailAndPassword(String email, String password);
}
