package com.users2.users2.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonIgnore
    private UserEntity usuario;

    // Relación con la tabla intermedia VentaVideojuegoEntity
    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<VentaVideojuegoEntity> ventaVideojuegos; // Relación con los videojuegos en la venta


    @Column(nullable = false)
    private double total;

    public void calcularTotal() {
        // Obtener el total inicial sumando todos los precios de los videojuegos
        double totalBase = ventaVideojuegos.stream()
                .mapToDouble(vv -> vv.getVideojuego().getPrecio() * vv.getCantidad())
                .sum();

        // Aplicar un 20% de descuento si el usuario es premium
        if (usuario.isPremium()) {
            this.total = totalBase * 0.8;  // Aplica el 20% de descuento
        } else {
            this.total = totalBase;  // Sin descuento si no es premium
        }
    }


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

    public List<VentaVideojuegoEntity> getVentaVideojuegos() {
        return ventaVideojuegos;
    }

    public void setVentaVideojuegos(List<VentaVideojuegoEntity> ventaVideojuegos) {
        this.ventaVideojuegos = ventaVideojuegos;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
