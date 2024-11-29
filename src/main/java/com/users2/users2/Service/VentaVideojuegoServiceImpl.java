package com.users2.users2.Service;

import com.users2.users2.Entity.VentaEntity;
import com.users2.users2.Entity.VentaVideojuegoEntity;
import com.users2.users2.Entity.VideojuegoEntity;
import com.users2.users2.Repository.VentaRepository;
import com.users2.users2.Repository.VentaVideojuegoRepository;
import com.users2.users2.Repository.VideoJuegoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VentaVideojuegoServiceImpl implements VentaVideojuegoService {

    @Autowired
    private VentaVideojuegoRepository ventaVideojuegoRepository;

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private VideoJuegoRepository videoJuegoRepository;

    @Override
    public VentaVideojuegoEntity agregarVideojuegoAVenta(int ventaId, int videojuegoId, int cantidad) {
        // Verificar si la venta y el videojuego existen
        Optional<VentaEntity> ventaOpt = ventaRepository.findById(ventaId);
        Optional<VideojuegoEntity> videojuegoOpt = videoJuegoRepository.findById(videojuegoId);

        if (ventaOpt.isPresent() && videojuegoOpt.isPresent()) {
            VentaEntity venta = ventaOpt.get();
            VideojuegoEntity videojuego = videojuegoOpt.get();

            // Crear la nueva entidad para la relación
            VentaVideojuegoEntity ventaVideojuego = new VentaVideojuegoEntity();
            ventaVideojuego.setVenta(venta);
            ventaVideojuego.setVideojuego(videojuego);
            ventaVideojuego.setCantidad(cantidad);

            return ventaVideojuegoRepository.save(ventaVideojuego);
        } else {
            throw new RuntimeException("Venta o Videojuego no encontrados.");
        }
    }

    @Override
    public List<VentaVideojuegoEntity> obtenerVideojuegosPorVenta(int ventaId) {
        return ventaVideojuegoRepository.findByVentaId(ventaId);
    }

    @Override
    public List<VentaVideojuegoEntity> obtenerVideojuegosPorVenta(VentaEntity venta) {
        return ventaVideojuegoRepository.findByVenta(venta);
    }

    @Override
    public VentaVideojuegoEntity actualizarCantidad(int ventaId, int videojuegoId, int nuevaCantidad) {
        // Buscar la relación existente
        Optional<VentaVideojuegoEntity> ventaVideojuegoOpt = ventaVideojuegoRepository.findByVentaIdAndVideojuegoId(ventaId, videojuegoId);

        if (ventaVideojuegoOpt.isPresent()) {
            VentaVideojuegoEntity ventaVideojuego = ventaVideojuegoOpt.get();
            ventaVideojuego.setCantidad(nuevaCantidad);
            return ventaVideojuegoRepository.save(ventaVideojuego);
        } else {
            throw new RuntimeException("La relación entre venta y videojuego no existe.");
        }
    }

    @Override
    public void eliminarVideojuegoDeVenta(int ventaId, int videojuegoId) {
        // Eliminar la relación entre venta y videojuego
        Optional<VentaVideojuegoEntity> ventaVideojuegoOpt = ventaVideojuegoRepository.findByVentaIdAndVideojuegoId(ventaId, videojuegoId);
        if (ventaVideojuegoOpt.isPresent()) {
            ventaVideojuegoRepository.delete(ventaVideojuegoOpt.get());
        } else {
            throw new RuntimeException("La relación entre venta y videojuego no existe.");
        }
    }

    @Override
    public List<VentaVideojuegoEntity> obtenerPorVideojuego(int videojuegoId) {
        // Buscar todas las relaciones por videojuego
        Optional<VideojuegoEntity> videojuegoOpt = videoJuegoRepository.findById(videojuegoId);
        if (videojuegoOpt.isPresent()) {
            return ventaVideojuegoRepository.findByVideojuego(videojuegoOpt.get());
        } else {
            throw new RuntimeException("Videojuego no encontrado.");
        }
    }
}
