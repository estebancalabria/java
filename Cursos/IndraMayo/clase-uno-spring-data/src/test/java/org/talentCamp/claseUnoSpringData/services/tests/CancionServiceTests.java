package org.talentCamp.claseUnoSpringData.services.tests;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.talentCamp.claseUnoSpringData.dto.CancionDTO;
import org.talentCamp.claseUnoSpringData.models.Artista;
import org.talentCamp.claseUnoSpringData.models.Cancion;
import org.talentCamp.claseUnoSpringData.repositories.CancionRepository;
import org.talentCamp.claseUnoSpringData.services.CancionService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CancionServiceTests {

    @Autowired
    CancionService service;

    @MockBean
    CancionRepository repository;

    @BeforeEach
    public void antes(){
        System.out.println("Antes de Cada Test");
        Mockito.when(this.repository.findById(1)).thenReturn(
                 Optional.of(Cancion.builder()
                        .id(1)
                         .titulo("Barbie de Extrarradio")
                         .artista(Artista.builder()
                         .nombre("Melendi").build() )
                         .fecha(LocalDate.of(2010, 1, 1))
                         .duracionSegundos(120)
                         .puntuaciones(new ArrayList<>())
                        .build()));
    }

    @Test
    public void testMockito(){
        CancionService mockService = Mockito.mock(CancionService.class);
        Mockito.when(mockService.recuperarPorId(1)).thenReturn(
                CancionDTO
                        .builder()
                        .nombre("Do I Wanna Know")
                        .año(LocalDate.of(2013,1,1))
                        .compositor("Arctic Monkeys")
                        .duracion(273)
                        .puntuacion("9")
                        .build());

        CancionDTO cancion = mockService.recuperarPorId(1);

        assertEquals("Do I Wanna Know",cancion.getNombre());
    }

    @Test
    void testRecuperarPorId_WithMockedRepository(){
        CancionDTO cancion = service.recuperarPorId(1);
        assertEquals("Barbie de Extrarradio", cancion.getNombre());
        assertEquals("Melendi", cancion.getCompositor());
        assertEquals(LocalDate.of(2010,1,1), cancion.getAño());
    }
}
