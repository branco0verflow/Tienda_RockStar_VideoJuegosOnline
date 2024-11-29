package com.users2.users2.DTO;

import java.util.List;

public class CompraPorFechaDTO {

    private String nombreUsuario;
    private double totalCompra;
    private List<VideojuegoCantidadDTO> videojuegos;

    public CompraPorFechaDTO(String nombreUsuario, double totalCompra, List<VideojuegoCantidadDTO> videojuegos) {
        this.nombreUsuario = nombreUsuario;
        this.totalCompra = totalCompra;
        this.videojuegos = videojuegos;
    }

    // Getters y Setters
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public double getTotalCompra() {
        return totalCompra;
    }

    public void setTotalCompra(double totalCompra) {
        this.totalCompra = totalCompra;
    }

    public List<VideojuegoCantidadDTO> getVideojuegos() {
        return videojuegos;
    }

    public void setVideojuegos(List<VideojuegoCantidadDTO> videojuegos) {
        this.videojuegos = videojuegos;
    }
}
