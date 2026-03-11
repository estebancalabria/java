package org.ministerioTrabajo.claseunospring.services;

import java.util.List;
import java.util.Optional;

import org.ministerioTrabajo.claseunospring.dto.AutomovilDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ministerioTrabajo.claseunospring.models.Automovil;
import org.ministerioTrabajo.claseunospring.repositories.GenericRepository;

@Service
public class AutomovilServiceImpl implements AutomovilService {
	
    @Autowired
    private GenericRepository<Automovil, Integer> repo;
    
    private AutomovilDto mapModelToDto(Automovil automovil) {
        AutomovilDto result = new AutomovilDto();
        result.setId(automovil.getId());
        result.setMatricula(automovil.getMatricula());
        result.setModelo(automovil.getModelo());
        return result;
    }

    private Automovil mapDtoToModel(AutomovilDto dto) {
        Automovil result = new Automovil();
        result.setId(dto.getId());
        result.setMatricula(dto.getMatricula());
        result.setModelo(dto.getModelo());
        return result;
    }

	@Override
	public List<AutomovilDto> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll().stream().map(this::mapModelToDto).toList();
	}

	@Override
	public AutomovilDto findById(int id) {
        Optional<Automovil> buscado = this.repo.findById(id);
        
        if (buscado.isEmpty()) {
            throw new NoEncontradoException("No existe automovil con id " + id);
        }
        
        return mapModelToDto(buscado.get());
	}

	@Override
	public void deleteById(int id) {
		 this.repo.deleteById(id);	
	}

	@Override
	public void agregar(AutomovilDto automovilDto) {
        Automovil automovil = this.mapDtoToModel(automovilDto);
        
        if ((automovil.getMatricula() == null) || automovil.getMatricula().isBlank()) {
            throw new ValidationException("La matrícula del automóvil no puede quedar vacía");
        }
        
        if ((automovil.getModelo() == null) || automovil.getModelo().isBlank()) {
            throw new ValidationException("El modelo del automóvil no puede quedar vacío");
        }
        
        // Validación para asegurarse de que no haya duplicados (si se necesita)
        if (this.repo.findAll().stream().filter(a -> a.getMatricula().equals(automovil.getMatricula())).findFirst().isPresent()) {
            throw new ElementoDuplicadoException("No se puede añadir un automóvil con la misma matrícula que otro existente");
        }
        
        this.repo.save(automovil);    	
	}

}
