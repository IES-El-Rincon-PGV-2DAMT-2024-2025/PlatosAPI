package com.pgv.restaurante.repository;

import com.pgv.restaurante.model.Cocinero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CocineroRepository extends JpaRepository<Cocinero, Long> {
}
