package com.users2.users2.Controller;

import com.users2.users2.Entity.VentaEntity;
import com.users2.users2.Entity.VideojuegoEntity;
import com.users2.users2.Service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @PostMapping
    public ResponseEntity<?> registrarVenta(@RequestBody Map<String, Object> requestData) {
        try {
            // Crear una nueva venta
            VentaEntity venta = new VentaEntity();
            venta.setFechaCompra(LocalDate.parse((String) requestData.get("fechaCompra")));
            venta.setUsuario(ventaService.getUsuarioById((Integer) requestData.get("usuarioId")));

            // Obtener lista de videojuegos vendidos y contar ocurrencias
            List<Integer> videojuegosIds = (List<Integer>) requestData.get("videojuegosIds");
            Map<Integer, Long> videojuegosCount = videojuegosIds.stream()
                    .collect(Collectors.groupingBy(id -> id, Collectors.counting()));

            // Buscar videojuegos y establecer la cantidad vendida
            List<VideojuegoEntity> videojuegos = ventaService.getVideojuegosByIds(new ArrayList<>(videojuegosCount.keySet()));
            for (VideojuegoEntity videojuego : videojuegos) {
                long cantidadVendida = videojuegosCount.get(videojuego.getId());
                videojuego.reducirCantidad((int) cantidadVendida);
            }

            venta.setVideojuegos(videojuegos);

            // Calcular total
            double total = videojuegos.stream()
                    .mapToDouble(videojuego -> videojuego.getPrecio() * videojuegosCount.get(videojuego.getId()))
                    .sum();
            venta.setTotal(total);

            // Guardar la venta
            VentaEntity ventaRegistrada = ventaService.save(venta);
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
        try {
            List<VentaEntity> ventas = ventaService.getVentasByUsuarioId(usuarioId);
            return ResponseEntity.ok(ventas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron ventas para el usuario: " + e.getMessage());
        }
    }
}

