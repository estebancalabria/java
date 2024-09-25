package org.ministerioTrabajo.claseunospring.services;

import java.util.List;

import org.ministerioTrabajo.claseunospring.dto.AutomovilDto;

public interface AutomovilService {
    List<AutomovilDto> findAll();
    AutomovilDto findById(int id);
    void deleteById(int id);
    void agregar(AutomovilDto automovilDto);
}
