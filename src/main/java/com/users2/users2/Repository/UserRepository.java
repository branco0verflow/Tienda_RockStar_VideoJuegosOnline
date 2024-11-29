package com.users2.users2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.users2.users2.Entity.UserEntity;

import java.time.LocalDate;
import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    // Buscar usuarios por email y password (para login)
    List<UserEntity> findByEmailAndPassword(String email, String password);

    // Buscar usuarios por tipo (Regular o Premium)
    List<UserEntity> findByIsPremium(boolean isPremium);


    // Buscar todos los usuarios registrados después de una fecha específica
    List<UserEntity> findByFechaRegistroAfter(LocalDate fecha);



}
