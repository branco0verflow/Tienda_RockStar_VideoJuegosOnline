package com.users2.users2.Service;

import com.users2.users2.DTO.CompraPorFechaDTO;
import com.users2.users2.DTO.CompraPorUsuarioDTO;
import com.users2.users2.DTO.VideojuegoCantidadDTO;
import com.users2.users2.Entity.UserEntity;
import com.users2.users2.Entity.VentaEntity;
import com.users2.users2.Entity.VideojuegoEntity;
import com.users2.users2.Repository.UserRepository;
import com.users2.users2.Repository.VentaRepository;
import com.users2.users2.Repository.VideoJuegoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VentaServiceImpl implements VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VideoJuegoRepository videojuegoRepository;

    @Override
    public VentaEntity save(VentaEntity ventaEntity) {
        return ventaRepository.save(ventaEntity);
    }

    @Override
    public boolean delete(int id) {
        if (ventaRepository.existsById(id)) {
            ventaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public VentaEntity getById(int id) {
        return ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada con ID: " + id));
    }

    @Override
    public List<VentaEntity> getAll() {
        return ventaRepository.findAll();
    }

    @Override
    public List<CompraPorUsuarioDTO> getVentasByUsuarioId(int usuarioId) {
        // Obtener las ventas por el ID del usuario
        List<VentaEntity> ventas = ventaRepository.findByUsuarioId(usuarioId);

        return ventas.stream().map(venta -> {
            // Mapear los videojuegos con sus cantidades
            List<VideojuegoCantidadDTO> videojuegosCantidad = venta.getVentaVideojuegos().stream()
                    .map(vv -> new VideojuegoCantidadDTO(vv.getVideojuego().getNombre(), vv.getCantidad()))
                    .collect(Collectors.toList());

            // Crear el DTO de compras por usuario con el nombre del usuario
            return new CompraPorUsuarioDTO(
                    venta.getUsuario().getNombre(), // Nombre del usuario
                    venta.getFechaCompra(),
                    videojuegosCantidad,
                    venta.getTotal()
            );
        }).collect(Collectors.toList());
    }



    @Override
    public List<VentaEntity> getVentasAfterDate(LocalDate fecha) {
        return ventaRepository.findByFechaCompraAfter(fecha);
    }

    @Override
    public UserEntity getUsuarioById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
    }

    @Override
    public List<VideojuegoEntity> getVideojuegosByIds(List<Integer> videojuegosIds) {
        if (videojuegosIds == null || videojuegosIds.isEmpty()) {
            throw new IllegalArgumentException("La lista de videojuegos IDs no puede estar vacía o nula");
        }
        return videojuegoRepository.findAllById(videojuegosIds);
    }


    // Método para actualizar la cantidad de los videojuegos
    public void actualizarCantidadVideojuego(int videojuegoId, int cantidadVendida) {
        VideojuegoEntity videojuego = videojuegoRepository.findById(videojuegoId)
                .orElseThrow(() -> new RuntimeException("Videojuego no encontrado con ID: " + videojuegoId));
        videojuego.reducirCantidad(cantidadVendida); // Método para reducir la cantidad
        videojuegoRepository.save(videojuego); // Guardar la actualización
    }

    @Override
    public List<CompraPorFechaDTO> getVentasByFechaCompra(LocalDate fechaCompra) {
        // Obtener las ventas por fecha desde el repositorio
        List<VentaEntity> ventas = ventaRepository.findByFechaCompra(fechaCompra);

        // Mapear las ventas a DTOs
        return ventas.stream().map(venta -> {
            // Obtener la lista de videojuegos con sus cantidades de cada venta
            List<VideojuegoCantidadDTO> videojuegosCantidad = venta.getVentaVideojuegos().stream()
                    .map(vv -> new VideojuegoCantidadDTO(vv.getVideojuego().getNombre(), vv.getCantidad()))
                    .collect(Collectors.toList());

            // Crear el DTO de compra por fecha con el nombre del usuario, total y los videojuegos
            return new CompraPorFechaDTO(
                    venta.getUsuario().getNombre(),
                    venta.getTotal(),
                    videojuegosCantidad
            );
        }).collect(Collectors.toList());
    }


}
