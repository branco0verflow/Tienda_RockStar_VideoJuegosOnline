package com.users2.users2.Repository;

import com.users2.users2.Entity.VentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<VentaEntity, Integer> {

    // Obtener todas las ventas realizadas por un usuario específico
    List<VentaEntity> findByUsuarioId(int usuarioId);

    // Obtener todas las ventas realizadas después de una fecha específica
    List<VentaEntity> findByFechaCompraAfter(LocalDate fecha);

    // Obtener el total de ventas realizadas por un usuario en una fecha específica
    @Query("SELECT v FROM VentaEntity v WHERE v.usuario.id = :usuarioId AND v.fechaCompra = :fecha")
    List<VentaEntity> findVentasByUsuarioIdAndFecha(int usuarioId, LocalDate fecha);
}

