package com.pgv.restaurante.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pgv.restaurante.ResourceNotFoundException;
import com.pgv.restaurante.model.*;
import com.pgv.restaurante.repository.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*") // Permite solicitudes desde Ionic
@RestController
@RequestMapping("/api/platos")
public class PlatoController {
    @Autowired
    private IngredienteRepository ingredienteRepository;

    @Autowired
    private PlatoRepository platoRepository;

    @Autowired
    private final CocineroRepository cocineroRepository;

    public PlatoController(PlatoRepository platoRepository, CocineroRepository cocineroRepository) {
        this.platoRepository = platoRepository;
        this.cocineroRepository = cocineroRepository;
    }

    @GetMapping
    public List<Plato> obtenerTodosLosPlatos() {
        return platoRepository.findAll();
    }

    @PostMapping
    public Plato crearPlato(@RequestBody Plato plato) {
        // Asegúrate de que el cocinero exista
        Long cocineroId = plato.getCocinero().getId();
        Cocinero cocinero = cocineroRepository.findById(cocineroId)
                .orElseThrow(() -> new RuntimeException("Cocinero no encontrado"));

        plato.setCocinero(cocinero);
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
    
        Set<Ingrediente> ingredientes = detallesPlato.getIngredientes();
        if (ingredientes != null) {
            ingredientes = ingredientes.stream()
                    .map(ingrediente -> ingredienteRepository.findById(ingrediente.getId())
                            .orElseThrow(() -> new ResourceNotFoundException("Ingrediente no encontrado")))
                    .collect(Collectors.toSet());
            plato.setIngredientes(ingredientes);
        }
    
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
