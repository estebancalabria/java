package org.talentCamp.claseUnoSpringData.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.talentCamp.claseUnoSpringData.models.Cancion;

@Mapper(componentModel = "spring")
public interface CancionMapper {
    CancionMapper INSTANCE = Mappers.getMapper( CancionMapper.class );

    @Mapping(target = "nombre", source = "titulo")
    @Mapping(target = "compositor", source = "artista")
    @Mapping(target = "duracion", source = "duracionSegundos")
    @Mapping(target = "año", source = "fecha")
    CancionDTO toDto(Cancion cancion);

    @Mapping(target = "titulo", source = "nombre")
    @Mapping(target = "artista", source = "compositor")
    @Mapping(target = "duracionSegundos", source = "duracion")
    @Mapping(target = "fecha", source = "año")
    Cancion fromDto(CancionDTO dto);
}