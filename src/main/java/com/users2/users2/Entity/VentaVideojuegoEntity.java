package com.users2.users2.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "venta_videojuego")
public class VentaVideojuegoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;  // Clave primaria única

    @ManyToOne
    @JoinColumn(name = "venta_id", nullable = false)
    private VentaEntity venta;  // Relación con la venta

    @ManyToOne
    @JoinColumn(name = "videojuego_id", nullable = false)
    private VideojuegoEntity videojuego;  // Relación con el videojuego

    @Column(name = "cantidad", nullable = false)
    private int cantidad;  // Cantidad del videojuego en la venta

    // Constructores
    public VentaVideojuegoEntity() {}

    public VentaVideojuegoEntity(VentaEntity venta, VideojuegoEntity videojuego, int cantidad) {
        this.venta = venta;
        this.videojuego = videojuego;
        this.cantidad = cantidad;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public VentaEntity getVenta() {
        return venta;
    }

    public void setVenta(VentaEntity venta) {
        this.venta = venta;
    }

    public VideojuegoEntity getVideojuego() {
        return videojuego;
    }

    public void setVideojuego(VideojuegoEntity videojuego) {
        this.videojuego = videojuego;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
