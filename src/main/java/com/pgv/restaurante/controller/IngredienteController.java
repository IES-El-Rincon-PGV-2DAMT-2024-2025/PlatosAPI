package com.pgv.restaurante.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.pgv.restaurante.model.Ingrediente;
import com.pgv.restaurante.model.Plato;
import com.pgv.restaurante.repository.IngredienteRepository;
import com.pgv.restaurante.ResourceNotFoundException;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/ingredientes")
public class IngredienteController {

    @Autowired
    private IngredienteRepository ingredienteRepository;

    @GetMapping
    public List<Ingrediente> obtenerTodosLosIngredientes() {
        return ingredienteRepository.findAll();
    }

    @PostMapping
    public Ingrediente crearIngrediente(@RequestBody Ingrediente ingrediente) {
        return ingredienteRepository.save(ingrediente);
    }

    @GetMapping("/{id}")
    public Ingrediente obtenerIngredientePorId(@PathVariable("id") Long id) {
        return ingredienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ingrediente no encontrado"));
    }

    @PutMapping("/{id}")
    public Ingrediente actualizarIngrediente(@PathVariable("id") Long id, @RequestBody Ingrediente detallesIngrediente) {
        Ingrediente ingrediente = ingredienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ingrediente no encontrado"));

        ingrediente.setNombre(detallesIngrediente.getNombre());

        return ingredienteRepository.save(ingrediente);
    }

    @DeleteMapping("/{id}")
    public Ingrediente eliminarIngrediente(@PathVariable("id") Long id) {
         Ingrediente ingrediente = ingredienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ingrediente no encontrado"));
        
        ingredienteRepository.deleteById(id);
        return ingrediente;
    }
}
