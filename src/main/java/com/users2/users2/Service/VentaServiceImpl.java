package com.users2.users2.Service;

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
    public List<VentaEntity> getVentasByUsuarioId(int usuarioId) {
        return ventaRepository.findByUsuarioId(usuarioId);
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
        return videojuegoRepository.findAllById(videojuegosIds);
    }
}
