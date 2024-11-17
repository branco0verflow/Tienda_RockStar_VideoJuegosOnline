package com.users2.users2.Service;

import com.users2.users2.Entity.VideojuegoEntity;

import java.util.List;

public interface VideoJuegoService {

        // Guardar o actualizar un videojuego
        VideojuegoEntity save(VideojuegoEntity videojuego);

        // Eliminar un videojuego por ID
        boolean delete(int id);

        // Obtener un videojuego por ID
        VideojuegoEntity getById(int id);

        // Obtener todos los videojuegos
        List<VideojuegoEntity> getAll();

        // Buscar videojuegos por categoría
        List<VideojuegoEntity> getByCategoria(String categoria);

        // Buscar videojuegos por código único
        VideojuegoEntity getByCodigo(String codigo);

        // Filtrar videojuegos por rango de precios
        List<VideojuegoEntity> getByPrecioBetween(double minPrecio, double maxPrecio);

        // Reducir stock de un videojuego
        boolean reducirStock(int id, int cantidadVendida);


        // Para buscar un VJ por nombre
        List<VideojuegoEntity> buscarPorNombre(String texto);
}


