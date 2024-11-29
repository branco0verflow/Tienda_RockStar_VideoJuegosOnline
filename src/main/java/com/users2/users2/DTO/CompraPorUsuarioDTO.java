package com.users2.users2.DTO;

import java.time.LocalDate;
import java.util.List;

public class CompraPorUsuarioDTO {
    private String nombreUsuario;
    private LocalDate fechaCompra;
    private List<VideojuegoCantidadDTO> videojuegos;
    private double totalCompra;

    // Constructor, getters y setters
    public CompraPorUsuarioDTO(String nombreUsuario, LocalDate fechaCompra, List<VideojuegoCantidadDTO> videojuegos, double totalCompra) {
        this.nombreUsuario = nombreUsuario;
        this.fechaCompra = fechaCompra;
        this.videojuegos = videojuegos;
        this.totalCompra = totalCompra;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public List<VideojuegoCantidadDTO> getVideojuegos() {
        return videojuegos;
    }

    public void setVideojuegos(List<VideojuegoCantidadDTO> videojuegos) {
        this.videojuegos = videojuegos;
    }

    public double getTotalCompra() {
        return totalCompra;
    }

    public void setTotalCompra(double totalCompra) {
        this.totalCompra = totalCompra;
    }
}

