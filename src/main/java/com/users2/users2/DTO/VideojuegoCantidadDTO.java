package com.users2.users2.DTO;

public class VideojuegoCantidadDTO {

    private String nombreVideojuego;
    private int cantidad;

    public VideojuegoCantidadDTO(String nombreVideojuego, int cantidad) {
        this.nombreVideojuego = nombreVideojuego;
        this.cantidad = cantidad;
    }

    // Getters y Setters
    public String getNombreVideojuego() {
        return nombreVideojuego;
    }

    public void setNombreVideojuego(String nombreVideojuego) {
        this.nombreVideojuego = nombreVideojuego;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
