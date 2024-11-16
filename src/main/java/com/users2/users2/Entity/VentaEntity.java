package com.users2.users2.Entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "venta")
public class VentaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private LocalDate fechaCompra;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false) // Relación con UserEntity
    private UserEntity usuario;

    @ManyToMany
    @JoinTable(
            name = "venta_videojuegos",
            joinColumns = @JoinColumn(name = "venta_id"),
            inverseJoinColumns = @JoinColumn(name = "videojuego_id")
    ) // Relación con VideojuegoEntity (lista de videojuegos en esta venta)
    private List<VideojuegoEntity> videojuegos;

    @Column(nullable = false)
    private double total;

    // Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public UserEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UserEntity usuario) {
        this.usuario = usuario;
    }

    public List<VideojuegoEntity> getVideojuegos() {
        return videojuegos;
    }

    public void setVideojuegos(List<VideojuegoEntity> videojuegos) {
        this.videojuegos = videojuegos;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    // Constructores

    public VentaEntity(int id, LocalDate fechaCompra, UserEntity usuario, List<VideojuegoEntity> videojuegos, double total) {
        this.id = id;
        this.fechaCompra = fechaCompra;
        this.usuario = usuario;
        this.videojuegos = videojuegos;
        this.total = total;
    }

    public VentaEntity() {
    }
}

