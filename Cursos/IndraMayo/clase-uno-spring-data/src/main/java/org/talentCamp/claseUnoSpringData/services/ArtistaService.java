package org.talentCamp.claseUnoSpringData.services;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.talentCamp.claseUnoSpringData.dto.ArtistaDTO;
import org.talentCamp.claseUnoSpringData.dto.ArtistaMapper;
import org.talentCamp.claseUnoSpringData.dto.CancionMapper;
import org.talentCamp.claseUnoSpringData.models.Artista;
import org.talentCamp.claseUnoSpringData.repositories.ArtistaRepository;

import java.util.*;

@Service
public class ArtistaService {

    @Autowired
    private ArtistaRepository repo;

    private final ArtistaMapper mapper = ArtistaMapper.INSTANCE;

    public List<ArtistaDTO> recuperarEmpiezanConD(){
        //Me lo completan uds?
        return this.repo.findEmpiezanConD().stream().map(mapper::toDto).toList();
    }

    public List<ArtistaDTO> recuperarEmpiezanCon(String comienzo){
        //Me lo completan uds?
        return this.repo.findEmpiezanCon(comienzo).stream().map(mapper::toDto).toList();
    }
}
