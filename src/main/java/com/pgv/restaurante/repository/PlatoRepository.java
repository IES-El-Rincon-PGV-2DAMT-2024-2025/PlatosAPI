package com.pgv.restaurante.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pgv.restaurante.model.Plato;

public interface PlatoRepository extends JpaRepository<Plato, Long> {
    // Métodos personalizados si es necesario
}

