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

    @ManyToMany(mappedBy = "videojuegos")
    @JsonIgnore // Evita que se serialicen las ventas asociadas a los videojuegos
    private List<VentaEntity> ventas; // Ventas en las que se incluye este videojuego

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

    public List<VentaEntity> getVentas() {
        return ventas;
    }

    public void setVentas(List<VentaEntity> ventas) {
        this.ventas = ventas;
    }

    // Constructores

    public VideojuegoEntity(int id, String codigo, String nombre, String descripcion, double precio,
                            String imagen, int cantidad, String categoria, List<VentaEntity> ventas) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen = imagen;
        this.cantidad = cantidad;
        this.categoria = categoria;
        this.ventas = ventas;
    }

    public VideojuegoEntity() {
    }

    public void reducirCantidad(int cantidadVendida) {
        if (this.cantidad >= cantidadVendida) {
            this.cantidad -= cantidadVendida;
        } else {
            throw new IllegalArgumentException("Stock insuficiente");
        }
    }


}
