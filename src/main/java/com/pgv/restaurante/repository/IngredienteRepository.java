package com.pgv.restaurante.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pgv.restaurante.model.Ingrediente;

@Repository
public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {
    // Métodos personalizados si los necesitas
}
