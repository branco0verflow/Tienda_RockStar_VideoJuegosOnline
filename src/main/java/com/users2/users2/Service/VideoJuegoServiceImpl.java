package com.users2.users2.Service;

import com.users2.users2.Entity.VideojuegoEntity;
import com.users2.users2.Repository.VideoJuegoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoJuegoServiceImpl implements VideoJuegoService {

    @Autowired
    private VideoJuegoRepository videojuegoRepository;

    // Guardar o actualizar un videojuego
    @Override
    public VideojuegoEntity save(VideojuegoEntity videojuego) {
        return videojuegoRepository.save(videojuego);
    }

    // Eliminar un videojuego por ID
    @Override
    public boolean delete(int id) {
        if (videojuegoRepository.existsById(id)) {
            videojuegoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Obtener un videojuego por ID
    @Override
    public VideojuegoEntity getById(int id) {
        return videojuegoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Videojuego no encontrado con ID: " + id));
    }

    // Obtener todos los videojuegos
    @Override
    public List<VideojuegoEntity> getAll() {
        return videojuegoRepository.findAll();
    }

    // Buscar videojuegos por categoría
    @Override
    public List<VideojuegoEntity> getByCategoria(String categoria) {
        return videojuegoRepository.findByCategoria(categoria);
    }

    // Buscar videojuegos por código único
    @Override
    public VideojuegoEntity getByCodigo(String codigo) {
        return videojuegoRepository.findByCodigo(codigo)
                .orElseThrow(() -> new RuntimeException("Videojuego no encontrado con código: " + codigo));
    }

    // Filtrar videojuegos por rango de precios
    @Override
    public List<VideojuegoEntity> getByPrecioBetween(double minPrecio, double maxPrecio) {
        return videojuegoRepository.findByPrecioBetween(minPrecio, maxPrecio);
    }

    // Reducir stock de un videojuego
    @Override
    public boolean reducirStock(int id, int cantidadVendida) {
        VideojuegoEntity videojuego = getById(id);
        try {
            videojuego.reducirCantidad(cantidadVendida);
            save(videojuego);
            return true;
        } catch (IllegalArgumentException e) {
            return false; // Stock insuficiente
        }
    }

    @Override
    public List<VideojuegoEntity> buscarPorNombre(String texto) {
        return videojuegoRepository.findByNombreContaining(texto);
    }


}

