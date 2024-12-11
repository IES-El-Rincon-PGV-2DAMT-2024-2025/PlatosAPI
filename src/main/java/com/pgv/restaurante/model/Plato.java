package com.pgv.restaurante.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "platos")
public record Plato(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id,

    @Column(nullable = false)
    String nombre,

    @Column(nullable = false)
    String descripcion,

    @Column(nullable = false)
    Double precio,

    @ManyToMany
    @JoinTable(
        name = "plato_ingrediente",
        joinColumns = @JoinColumn(name = "plato_id"),
        inverseJoinColumns = @JoinColumn(name = "ingrediente_id")
    )
    Set<Ingrediente> ingredientes,

    @ManyToOne
    @JoinColumn(name = "cocinero_id")
    Cocinero cocinero
) {
    public Plato(String nombre, String descripcion, Double precio, Set<Ingrediente> ingredientes, Cocinero cocinero) {
        this(null, nombre, descripcion, precio, ingredientes, cocinero);
    }
}
