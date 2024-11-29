package com.users2.users2.Repository;

import com.users2.users2.Entity.VideojuegoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VideoJuegoRepository extends JpaRepository<VideojuegoEntity, Integer> {

    // Buscar videojuegos por categoría
    List<VideojuegoEntity> findByCategoria(String categoria);

    // Buscar videojuego por código único
    Optional<VideojuegoEntity> findByCodigo(String codigo);

    // Filtrar videojuegos por rango de precios
    List<VideojuegoEntity> findByPrecioBetween(double minPrecio, double maxPrecio);

    List<VideojuegoEntity> findByCantidadLessThan(int cantidad);

    // Buscar videojuegos con nombre que contenga un texto específico (parcial)
    List<VideojuegoEntity> findByNombreContaining(String texto);
}

