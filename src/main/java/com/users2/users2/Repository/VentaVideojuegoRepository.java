package com.users2.users2.Repository;

import com.users2.users2.Entity.VentaEntity;
import com.users2.users2.Entity.VentaVideojuegoEntity;
import com.users2.users2.Entity.VideojuegoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VentaVideojuegoRepository extends JpaRepository<VentaVideojuegoEntity, Integer> {

    // Consultas personalizadas usando las claves foráneas
    List<VentaVideojuegoEntity> findByVentaId(int ventaId);

    List<VentaVideojuegoEntity> findByVenta(VentaEntity venta);

    List<VentaVideojuegoEntity> findByVideojuego(VideojuegoEntity videojuego);

    Optional<VentaVideojuegoEntity> findByVentaIdAndVideojuegoId(int ventaId, int videojuegoId);
}
