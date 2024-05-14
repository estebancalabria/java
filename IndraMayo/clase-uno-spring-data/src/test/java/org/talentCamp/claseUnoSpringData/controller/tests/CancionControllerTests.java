package org.talentCamp.claseUnoSpringData.controller.tests;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.talentCamp.claseUnoSpringData.controllers.CancionController;
import org.talentCamp.claseUnoSpringData.dto.CancionDTO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Profile("test")
@Transactional
public class CancionControllerTests {

    @Autowired
    CancionController controller;

    @Test
    void testGetTodas(){
        ResponseEntity<List<CancionDTO>> respuesta = controller.getTodas();
        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertTrue(respuesta.getBody().size() > 0);
        assertEquals("David Bisbal", respuesta.getBody().stream().findFirst().get().getCompositor());

    }
}
