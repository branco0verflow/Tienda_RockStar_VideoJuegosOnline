package com.users2.users2.Controller;

import com.users2.users2.DTO.CompraPorFechaDTO;
import com.users2.users2.DTO.CompraPorUsuarioDTO;
import com.users2.users2.Entity.VentaEntity;
import com.users2.users2.Entity.VideojuegoEntity;
import com.users2.users2.Entity.VentaVideojuegoEntity; // Importar la entidad intermedia
import com.users2.users2.Repository.VentaRepository;
import com.users2.users2.Repository.VentaVideojuegoRepository;
import com.users2.users2.Repository.VideoJuegoRepository;
import com.users2.users2.Service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @Autowired
    private VideoJuegoRepository videojuegoRepository;  // Inyectar el repositorio

    @Autowired
    private VentaRepository ventaRepository;  // Inyectar el repositorio

    @Autowired
    private VentaVideojuegoRepository ventaVideojuegoRepository; // Inyectar el repositorio de la tabla intermedia


    @PostMapping
    public ResponseEntity<?> registrarVenta(@RequestBody Map<String, Object> requestData) {
        try {
            // Crear una nueva venta
            VentaEntity venta = new VentaEntity();
            venta.setFechaCompra(LocalDate.parse((String) requestData.get("fechaCompra")));
            venta.setUsuario(ventaService.getUsuarioById((Integer) requestData.get("usuarioId")));

            // Obtener el estado de premium desde el requestData
            Boolean isPremium = (Boolean) requestData.get("isPremium");

            // Obtener la lista de videojuegos con sus cantidades
            List<Map<String, Object>> videojuegosData = (List<Map<String, Object>>) requestData.get("videojuegos");

            // Crear la lista de relaciones venta-videojuego
            List<VentaVideojuegoEntity> ventaVideojuegos = new ArrayList<>();
            double total = 0.0; // Total sin descuento

            for (Map<String, Object> videojuegoData : videojuegosData) {
                Integer videojuegoId = (Integer) videojuegoData.get("videojuegoId");
                Integer cantidad = (Integer) videojuegoData.get("cantidad");

                // Buscar el videojuego y reducir su stock
                Optional<VideojuegoEntity> videojuegoOpt = videojuegoRepository.findById(videojuegoId);
                if (videojuegoOpt.isPresent()) {
                    VideojuegoEntity videojuego = videojuegoOpt.get();

                    // Reducir la cantidad del videojuego en stock
                    videojuego.reducirCantidad(cantidad);
                    videojuegoRepository.save(videojuego);  // Actualizar el videojuego en la base de datos

                    // Crear la relación venta-videojuego
                    VentaVideojuegoEntity ventaVideojuego = new VentaVideojuegoEntity(venta, videojuego, cantidad);
                    ventaVideojuegos.add(ventaVideojuego);

                    // Sumar el precio del videojuego al total
                    total += videojuego.getPrecio() * cantidad;
                }
            }

            System.out.println("isPremium: " + isPremium);

            // Aplicar descuento si es usuario premium
            if (isPremium != null && isPremium) {
                total = total * 0.8; // Aplica un descuento del 20%
            }

            // Guardar la venta y las relaciones en la base de datos
            VentaEntity ventaRegistrada = ventaService.save(venta);
            ventaVideojuegoRepository.saveAll(ventaVideojuegos);

            // Setear el total con el descuento aplicado
            ventaRegistrada.setTotal(total);

            // Guardar la venta con el total calculado
            ventaService.save(ventaRegistrada);

            return ResponseEntity.status(HttpStatus.CREATED).body(ventaRegistrada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al registrar la venta: " + e.getMessage());
        }
    }







    @GetMapping
    public ResponseEntity<List<VentaEntity>> obtenerTodasLasVentas() {
        return ResponseEntity.ok(ventaService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerVentaPorId(@PathVariable int id) {
        try {
            VentaEntity venta = ventaService.getById(id);
            return ResponseEntity.ok(venta);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Venta no encontrada: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarVenta(@PathVariable int id) {
        if (ventaService.delete(id)) {
            return ResponseEntity.ok("Venta eliminada con éxito");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Venta no encontrada");
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> obtenerVentasPorUsuario(@PathVariable int usuarioId) {
        List<CompraPorUsuarioDTO> ventas = ventaService.getVentasByUsuarioId(usuarioId);
        if (ventas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron ventas para el usuario con ID: " + usuarioId);
        }
        return ResponseEntity.ok(ventas);
    }

    @GetMapping("/fecha/{fechaCompra}")
    public ResponseEntity<?> obtenerVentasPorFechaCompra(@PathVariable String fechaCompra) {
        try {
            LocalDate fecha = LocalDate.parse(fechaCompra);
            List<CompraPorFechaDTO> ventas = ventaService.getVentasByFechaCompra(fecha);
            return ResponseEntity.ok(ventas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al procesar la fecha: " + e.getMessage());
        }
    }
}
