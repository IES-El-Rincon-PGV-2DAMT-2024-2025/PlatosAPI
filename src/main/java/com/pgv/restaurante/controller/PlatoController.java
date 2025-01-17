package com.pgv.restaurante.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.pgv.restaurante.ResourceNotFoundException;
import com.pgv.restaurante.model.Ingrediente;
import com.pgv.restaurante.model.Plato;
import com.pgv.restaurante.repository.IngredienteRepository;
import com.pgv.restaurante.repository.PlatoRepository;

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

    @GetMapping
    public List<Plato> obtenerTodosLosPlatos() {
        return platoRepository.findAll();
    }

    @PostMapping()
    public Plato crearPlato(@RequestBody Plato plato) {
        // Asegúrate de que los ingredientes existen antes de asociarlos
        Set<Ingrediente> ingredientes = plato.getIngredientes();
        if (ingredientes != null) {
            ingredientes = ingredientes.stream()
                    .map(ingrediente -> ingredienteRepository.findById(ingrediente.getId())
                            .orElseThrow(() -> new ResourceNotFoundException("Ingrediente no encontrado")))
                    .collect(Collectors.toSet());
            plato.setIngredientes(ingredientes);
        }
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
