package com.users2.users2.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.users2.users2.Entity.UserEntity;
import com.users2.users2.Service.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Agregar usuario
    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody Map<String, Object> requestData) {
        UserEntity user = new UserEntity();
        user.setEmail((String) requestData.get("email"));
        user.setPassword((String) requestData.get("password"));
        user.setNombre((String) requestData.get("nombre"));
        user.setFechaRegistro(LocalDate.now());

        // Asignar valor booleano a isPremium según el tipoUsuario
        String tipoUsuario = (String) requestData.get("tipoUsuario");
        user.setPremium("premium".equalsIgnoreCase(tipoUsuario)); // true si es "premium", false si es "regular"

        user.setNumeroTarjeta((String) requestData.get("numeroTarjeta"));

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }


    // Actualizar usuario
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody Map<String, Object> requestData) {
        UserEntity user = userService.getById(id);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
        user.setEmail((String) requestData.get("email"));
        user.setPassword((String) requestData.get("password"));
        user.setNombre((String) requestData.get("nombre"));

        // Asignar valor booleano a isPremium según el tipoUsuario
        String tipoUsuario = (String) requestData.get("tipoUsuario");
        user.setPremium("premium".equalsIgnoreCase(tipoUsuario)); // true si es "premium", false si es "regular"

        user.setNumeroTarjeta((String) requestData.get("numeroTarjeta"));

        return ResponseEntity.status(HttpStatus.OK).body(userService.save(user));
    }


    // Eliminar usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        boolean isDeleted = userService.delete(id);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body("Usuario eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
    }

    // Obtener usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable int id) {
        UserEntity user = userService.getById(id);
        if (user != null) {
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
    }

    // Obtener todos los usuarios
    @GetMapping
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAll());
    }

    // Login de usuario
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, Object> loginData) {
        String email = (String) loginData.get("email");
        String password = (String) loginData.get("password");
        UserEntity user = userService.login(email, password);
        if (user != null) {
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }

    // Obtener usuarios por tipo (regular o premium)
    @GetMapping("/tipo/{tipoUsuario}")
    public ResponseEntity<?> getUsersByType(@PathVariable String tipoUsuario) {
        List<UserEntity> users = userService.getByTipoUsuario(tipoUsuario);
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    // Buscar usuario por número de tarjeta
    @GetMapping("/tarjeta/{numeroTarjeta}")
    public ResponseEntity<?> getUserByCard(@PathVariable String numeroTarjeta) {
        UserEntity user = userService.getByNumeroTarjeta(numeroTarjeta);
        if (user != null) {
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario con número de tarjeta no encontrado");
        }
    }
}
