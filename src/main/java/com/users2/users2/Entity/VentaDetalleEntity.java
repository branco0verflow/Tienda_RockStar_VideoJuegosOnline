package com.users2.users2.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "venta_detalle")
public class VentaDetalleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "venta_id", nullable = false)
    private VentaEntity venta;

    @ManyToOne
    @JoinColumn(name = "videojuego_id", nullable = false)
    private VideojuegoEntity videojuego;

    @Column(nullable = false)
    private int cantidad;

    @Column(nullable = false)
    private double precioTotal;

    // Getters y Setters
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

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }
}

