package com.users2.users2.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "videojuego")
public class VideojuegoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String codigo; // Código único del videojuego

    @Column(nullable = false)
    private String nombre;

    @Column
    private String descripcion;

    @Column(nullable = false)
    private double precio;

    @Column
    private String imagen; // Ruta o URL de la imagen del videojuego

    @Column(nullable = false)
    private int cantidad; // Stock disponible del videojuego

    @Column(nullable = false)
    private String categoria; // Categoría como acción, aventura, deportes, etc.

    // Relación con la tabla intermedia VentaVideojuegoEntity
    @OneToMany(mappedBy = "videojuego", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<VentaVideojuegoEntity> ventasVideojuegos; // Relación con las ventas que contienen este videojuego

    // Método para reducir la cantidad de stock
    public void reducirCantidad(int cantidadVendida) {
        if (this.cantidad >= cantidadVendida) {
            this.cantidad -= cantidadVendida;
        } else {
            throw new IllegalArgumentException("Stock insuficiente");
        }
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public List<VentaVideojuegoEntity> getVentasVideojuegos() {
        return ventasVideojuegos;
    }

    public void setVentasVideojuegos(List<VentaVideojuegoEntity> ventasVideojuegos) {
        this.ventasVideojuegos = ventasVideojuegos;
    }
}
