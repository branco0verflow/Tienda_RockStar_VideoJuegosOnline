package com.users2.users2.Controller;

import com.users2.users2.Entity.Administrador;
import com.users2.users2.Repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/administradores")
public class AdministradorController {

    @Autowired
    private AdministradorRepository administradorRepository;

    @PostMapping("/login")
    public ResponseEntity<Boolean> autenticarAdministrador(@RequestBody Map<String, String> datos) {
        String email = datos.get("email");
        String password = datos.get("password");

        // Buscar administrador por email
        Optional<Administrador> adminOpt = administradorRepository.findByEmail(email);

        if (adminOpt.isPresent()) {
            Administrador admin = adminOpt.get();
            // Comparar contraseñas
            if (admin.getPassword().equals(password)) {
                return ResponseEntity.ok(true); // Credenciales válidas
            }
        }
        return ResponseEntity.ok(false); // Credenciales inválidas
    }
}

