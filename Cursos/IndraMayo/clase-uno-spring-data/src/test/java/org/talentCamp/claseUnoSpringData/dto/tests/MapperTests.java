package org.talentCamp.claseUnoSpringData.dto.tests;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.talentCamp.claseUnoSpringData.dto.ArtistaDTO;
import org.talentCamp.claseUnoSpringData.dto.ArtistaMapper;
import org.talentCamp.claseUnoSpringData.models.Artista;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MapperTests {

    @Test
    public void testArtistaModelToDto(){
        Artista artista = Artista.builder().nombre("Oliver Heldels").build();
        ArtistaDTO artistaDTO = ArtistaMapper.INSTANCE.toDto(artista);
        assertEquals(artista.getNombre(),artistaDTO.getNombre());
    }
}
