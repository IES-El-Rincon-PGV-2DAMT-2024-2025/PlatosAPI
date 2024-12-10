package com.pgv.restaurante.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "platos")
public class Plato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private Double precio;

    // Relación con Ingredientes (opcional, si ya existe)
    @ManyToMany
    @JoinTable(
        name = "plato_ingrediente",
        joinColumns = @JoinColumn(name = "plato_id"),
        inverseJoinColumns = @JoinColumn(name = "ingrediente_id")
    )
    private Set<Ingrediente> ingredientes;

    // Un Plato es hecho por un Cocinero
    @ManyToOne
    @JoinColumn(name = "cocinero_id")
    private Cocinero cocinero;

    // Constructores
    public Plato() {}

    public Plato(String nombre, String descripcion, Double precio, Cocinero cocinero) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cocinero = cocinero;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Set<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(Set<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public Cocinero getCocinero() {
        return cocinero;
    }

    public void setCocinero(Cocinero cocinero) {
        this.cocinero = cocinero;
    }
}
