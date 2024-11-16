package com.users2.users2.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.users2.users2.Entity.UserEntity;
import com.users2.users2.Repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    // Guardar o actualizar un usuario
    @Override
    public UserEntity save(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    // Eliminar un usuario por ID
    @Override
    public boolean delete(int id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Obtener un usuario por ID
    @Override
    public UserEntity getById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
    }

    // Obtener todos los usuarios
    @Override
    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    // Login de usuario
    @Override
    public UserEntity login(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password)
                .stream()
                .findFirst()
                .orElse(null); // Retorna el usuario si las credenciales coinciden
    }

    // Obtener usuarios por tipo (regular o premium)
    @Override
    public List<UserEntity> getByTipoUsuario(String tipoUsuario) {
        return userRepository.findByTipoUsuario(tipoUsuario);
    }

    // Buscar un usuario por número de tarjeta
    @Override
    public UserEntity getByNumeroTarjeta(String numeroTarjeta) {
        return userRepository.findByNumeroTarjeta(numeroTarjeta);
    }

    // Obtener usuarios registrados después de una fecha específica
    @Override
    public List<UserEntity> getByFechaRegistroAfter(LocalDate fecha) {
        return userRepository.findByFechaRegistroAfter(fecha);
    }
}

