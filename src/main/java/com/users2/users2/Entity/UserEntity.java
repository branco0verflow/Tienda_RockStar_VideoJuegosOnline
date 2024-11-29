package com.users2.users2.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private LocalDate fechaRegistro;

    @Column
    private boolean isPremium;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<VentaEntity> historialCompras;

    // Eliminamos la propiedad numeroTarjeta

    // Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public boolean isPremium() {
        return isPremium;
    }

    public void setPremium(boolean isPremium) {
        this.isPremium = isPremium;
    }

    public List<VentaEntity> getHistorialCompras() {
        return historialCompras;
    }

    public void setHistorialCompras(List<VentaEntity> historialCompras) {
        this.historialCompras = historialCompras;
    }

    // Constructores

    public UserEntity(int id, String email, String password, String nombre,
                      LocalDate fechaRegistro, boolean isPremium, List<VentaEntity> historialCompras) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nombre = nombre;
        this.fechaRegistro = fechaRegistro;
        this.isPremium = isPremium;
        this.historialCompras = historialCompras;
    }

    public UserEntity() {
    }
}
