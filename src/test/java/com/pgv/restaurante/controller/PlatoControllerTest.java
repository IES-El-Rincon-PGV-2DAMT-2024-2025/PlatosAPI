package com.pgv.restaurante.controller;

import com.pgv.restaurante.model.Plato;
import com.pgv.restaurante.repository.PlatoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// Levanta la aplicación en un puerto aleatorio para pruebas
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class PlatoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PlatoRepository platoRepository;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        // Limpia y rellena la base de datos antes de cada test
        platoRepository.deleteAll();
        platoRepository.save(new Plato("Hamburguesa", "Rica", 5.00, null));
        platoRepository.save(new Plato("Pizza", "Rica", 10.00, null));
    }

    @Test
    void getAllPlatos_shouldReturnListOfPlatos() throws Exception {
        mockMvc.perform(get("/api/platos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].nombre").exists())
                .andExpect(jsonPath("$[1].nombre").exists());
    }
}
