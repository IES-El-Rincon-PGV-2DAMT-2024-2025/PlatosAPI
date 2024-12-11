package com.pgv.restaurante.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;

@Entity
@Table(name = "ingredientes")
public record Ingrediente(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id,

    @Column(nullable = false)
    String nombre,

    @ManyToMany(mappedBy = "ingredientes")
    @JsonIgnore
    Set<Plato> platos
) {
    public Ingrediente(String nombre) {
        this(null, nombre, null);
    }
}
