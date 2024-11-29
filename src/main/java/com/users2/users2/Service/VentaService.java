package com.users2.users2.Service;

import com.users2.users2.DTO.CompraPorFechaDTO;
import com.users2.users2.DTO.CompraPorUsuarioDTO;
import com.users2.users2.Entity.UserEntity;
import com.users2.users2.Entity.VentaEntity;
import com.users2.users2.Entity.VideojuegoEntity;

import java.time.LocalDate;
import java.util.List;

public interface VentaService {

    // Guardar o actualizar una venta
    VentaEntity save(VentaEntity ventaEntity);

    // Eliminar una venta por ID
    boolean delete(int id);

    // Obtener una venta por ID
    VentaEntity getById(int id);

    // Obtener todas las ventas
    List<VentaEntity> getAll();

    // Obtener ventas realizadas por un usuario específico
    List<CompraPorUsuarioDTO> getVentasByUsuarioId(int usuarioId);

    // Obtener ventas realizadas después de una fecha específica
    List<VentaEntity> getVentasAfterDate(LocalDate fecha);

    // Obtener un usuario por ID (usado al crear ventas)
    UserEntity getUsuarioById(int id);

    // Obtener videojuegos por una lista de IDs (usado al crear ventas)
    List<VideojuegoEntity> getVideojuegosByIds(List<Integer> videojuegosIds);

    List<CompraPorFechaDTO> getVentasByFechaCompra(LocalDate fechaCompra);

}
