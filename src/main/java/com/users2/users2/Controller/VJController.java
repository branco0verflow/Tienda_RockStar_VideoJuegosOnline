package com.users2.users2.Controller;
import com.users2.users2.Entity.VideojuegoEntity;
import com.users2.users2.Repository.VideoJuegoRepository;
import com.users2.users2.Service.VideoJuegoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/videoJuegos")
public class VJController {

    @Autowired
    private VideoJuegoService videojuegoService;

    @Autowired
    private VideoJuegoRepository videojuegoRepository;


    // Agregar un nuevo videojuego
    @PostMapping
    public ResponseEntity<?> addVideojuego(@RequestBody VideojuegoEntity videojuego) {
        return ResponseEntity.status(HttpStatus.CREATED).body(videojuegoService.save(videojuego));
    }

    // Actualizar un videojuego
    @PutMapping("/{id}")
    public ResponseEntity<?> updateVideojuego(@PathVariable int id, @RequestBody VideojuegoEntity videojuegoDetails) {
        VideojuegoEntity existingVideojuego = videojuegoService.getById(id);
        if (existingVideojuego == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Videojuego no encontrado");
        }
        existingVideojuego.setCodigo(videojuegoDetails.getCodigo());
        existingVideojuego.setNombre(videojuegoDetails.getNombre());
        existingVideojuego.setDescripcion(videojuegoDetails.getDescripcion());
        existingVideojuego.setPrecio(videojuegoDetails.getPrecio());
        existingVideojuego.setImagen(videojuegoDetails.getImagen());
        existingVideojuego.setCantidad(videojuegoDetails.getCantidad());
        existingVideojuego.setCategoria(videojuegoDetails.getCategoria());

        return ResponseEntity.status(HttpStatus.OK).body(videojuegoService.save(existingVideojuego));
    }

    // Eliminar un videojuego por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVideojuego(@PathVariable int id) {
        if (videojuegoService.delete(id)) {
            return ResponseEntity.status(HttpStatus.OK).body("Videojuego eliminado con éxito");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Videojuego no encontrado");
        }
    }

    // Obtener un videojuego por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getVideojuego(@PathVariable int id) {
        VideojuegoEntity videojuego = videojuegoService.getById(id);
        if (videojuego == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Videojuego no encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(videojuego);
    }

    // Listar todos los videojuegos
    @GetMapping
    public ResponseEntity<List<VideojuegoEntity>> getAllVideojuegos() {
        return ResponseEntity.status(HttpStatus.OK).body(videojuegoService.getAll());
    }

    // Reducir la cantidad de un videojuego (stock)
    @PostMapping("/{id}/reducir")
    public ResponseEntity<?> reducirCantidad(@PathVariable int id, @RequestBody Map<String, Integer> request) {
        int cantidad = request.getOrDefault("cantidad", 0);
        try {
            VideojuegoEntity videojuego = videojuegoService.getById(id);
            if (videojuego == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Videojuego no encontrado");
            }
            videojuego.reducirCantidad(cantidad);
            return ResponseEntity.status(HttpStatus.OK).body(videojuegoService.save(videojuego));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/stock/{cantidad}")
    public ResponseEntity<?> getVideojuegosPorStock(@PathVariable int cantidad) {
        try {
            List<VideojuegoEntity> videojuegos = videojuegoService.getVideojuegosConStockMenorA(cantidad);
            if (videojuegos.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron videojuegos con stock menor a " + cantidad);
            }
            return ResponseEntity.ok(videojuegos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener los videojuegos: " + e.getMessage());
        }
    }



}
