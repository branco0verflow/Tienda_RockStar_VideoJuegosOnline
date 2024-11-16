package com.users2.users2.Service;

import java.time.LocalDate;
import java.util.List;
import com.users2.users2.Entity.UserEntity;

public interface UserService {

    // Guardar o actualizar un usuario
    UserEntity save(UserEntity userEntity);

    // Eliminar un usuario por ID
    boolean delete(int id);

    // Obtener un usuario por ID
    UserEntity getById(int id);

    // Obtener todos los usuarios
    List<UserEntity> getAll();

    // Login de usuario
    UserEntity login(String email, String password);

    // Obtener usuarios por tipo (regular o premium)
    List<UserEntity> getByTipoUsuario(String tipoUsuario);

    // Buscar un usuario por número de tarjeta
    UserEntity getByNumeroTarjeta(String numeroTarjeta);

    // Obtener usuarios registrados después de una fecha específica
    List<UserEntity> getByFechaRegistroAfter(LocalDate fecha);
}

