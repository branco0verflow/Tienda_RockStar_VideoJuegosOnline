package com.users2.users2.Service;

import com.users2.users2.Entity.VentaEntity;
import com.users2.users2.Entity.VentaVideojuegoEntity;

import java.util.List;

public interface VentaVideojuegoService {

    VentaVideojuegoEntity agregarVideojuegoAVenta(int ventaId, int videojuegoId, int cantidad);

    List<VentaVideojuegoEntity> obtenerVideojuegosPorVenta(int ventaId);

    List<VentaVideojuegoEntity> obtenerVideojuegosPorVenta(VentaEntity venta);

    VentaVideojuegoEntity actualizarCantidad(int ventaId, int videojuegoId, int nuevaCantidad);

    void eliminarVideojuegoDeVenta(int ventaId, int videojuegoId);

    List<VentaVideojuegoEntity> obtenerPorVideojuego(int videojuegoId);
}

