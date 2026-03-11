package org.talentCamp.claseUnoSpringData.dto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.talentCamp.claseUnoSpringData.models.Artista;
import org.talentCamp.claseUnoSpringData.models.Cancion;

@Mapper(componentModel = "spring")
public interface ArtistaMapper {
    ArtistaMapper INSTANCE = Mappers.getMapper( ArtistaMapper.class );

    ArtistaDTO toDto(Artista cancion);
    Artista toDto(ArtistaDTO cancion);
}
