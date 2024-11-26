package com.pgv.restaurante.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pgv.restaurante.ResourceNotFoundException;
import com.pgv.restaurante.model.Plato;
import com.pgv.restaurante.repository.PlatoRepository;

import java.util.List;

@CrossOrigin(origins = "*") // Permite solicitudes desde Ionic
@RestController
@RequestMapping("/api/platos")
public class PlatoController {

    @Autowired
    private PlatoRepository platoRepository;

    @GetMapping
    public List<Plato> obtenerTodosLosPlatos() {
        return platoRepository.findAll();
    }

    @PostMapping
    public Plato crearPlato(@RequestBody Plato plato) {
        return platoRepository.save(plato);
    }

    @GetMapping("/{id}")
    public Plato obtenerPlatoPorId(@PathVariable("id") Long id) {
        return platoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plato no encontrado"));
    }

    @PutMapping("/{id}")
    public Plato actualizarPlato(@PathVariable("id") Long id, @RequestBody Plato detallesPlato) {
        Plato plato = platoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plato no encontrado"));

        plato.setNombre(detallesPlato.getNombre());
        plato.setDescripcion(detallesPlato.getDescripcion());
        plato.setPrecio(detallesPlato.getPrecio());

        return platoRepository.save(plato);
    }

    @DeleteMapping("/{id}")
    public Plato eliminarPlato(@PathVariable("id") Long id) {
        Plato plato = platoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plato no encontrado"));
        platoRepository.deleteById(id);
        return plato;
    }
}
