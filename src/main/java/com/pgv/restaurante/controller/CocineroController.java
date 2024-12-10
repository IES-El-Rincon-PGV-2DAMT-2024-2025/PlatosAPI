package com.pgv.restaurante.controller;

import com.pgv.restaurante.model.Cocinero;
import com.pgv.restaurante.repository.CocineroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/cocineros")
public class CocineroController {

    @Autowired
    private CocineroRepository cocineroRepository;

    @GetMapping
    public List<Cocinero> getAllCocineros() {
        return cocineroRepository.findAll();
    }

    @GetMapping("/{id}")
    public Cocinero getCocineroById(@PathVariable Long id) {
        return cocineroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cocinero no encontrado"));
    }

    @PostMapping
    public Cocinero createCocinero(@RequestBody Cocinero cocinero) {
        return cocineroRepository.save(cocinero);
    }

    @PutMapping("/{id}")
    public Cocinero updateCocinero(@PathVariable Long id, @RequestBody Cocinero cocineroDetails) {
        Cocinero cocinero = cocineroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cocinero no encontrado"));

        cocinero.setNombre(cocineroDetails.getNombre());
        return cocineroRepository.save(cocinero);
    }

    @DeleteMapping("/{id}")
    public String deleteCocinero(@PathVariable Long id) {
        Optional<Cocinero> cocinero = cocineroRepository.findById(id);
        if (cocinero.isPresent()) {
            cocineroRepository.delete(cocinero.get());
            return "Cocinero eliminado";
        } else {
            throw new RuntimeException("Cocinero no encontrado");
        }
    }
}
