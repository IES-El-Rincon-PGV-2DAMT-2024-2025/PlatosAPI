package com.pgv.restaurante.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "cocineros")
public record Cocinero(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id,

    @Column(nullable = false)
    String nombre,

    @JsonIgnore
    @OneToMany(mappedBy = "cocinero", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Plato> platos
) {}
