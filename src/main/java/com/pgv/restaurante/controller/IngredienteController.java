package com.pgv.restaurante.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;
import com.pgv.restaurante.model.Ingrediente;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/ingredientes")
public class IngredienteController {

    @Autowired
    private JpaRepository<Ingrediente, Long> ingredienteRepository;

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
                .orElseThrow(() -> new RuntimeException("Ingrediente no encontrado"));
    }

    @PutMapping("/{id}")
    public Ingrediente actualizarIngrediente(@PathVariable("id") Long id, @RequestBody Ingrediente detallesIngrediente) {
        Ingrediente ingrediente = ingredienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingrediente no encontrado"));

        
        return ingredienteRepository.save(new Ingrediente(ingrediente.nombre()));
    }

    @DeleteMapping("/{id}")
    public Ingrediente eliminarIngrediente(@PathVariable("id") Long id) {
         Ingrediente ingrediente = ingredienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingrediente no encontrado"));
        
        ingredienteRepository.deleteById(id);
        return ingrediente;
    }
}
