package org.talentCamp.claseUnoSpringData.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.talentCamp.claseUnoSpringData.dto.CancionDTO;
import org.talentCamp.claseUnoSpringData.dto.CancionMapper;
import org.talentCamp.claseUnoSpringData.models.Cancion;
import org.talentCamp.claseUnoSpringData.models.Puntuacion;
import org.talentCamp.claseUnoSpringData.repositories.ArtistaRepository;
import org.talentCamp.claseUnoSpringData.repositories.CancionRepository;

import java.text.MessageFormat;
import java.util.List;

@Service
public class CancionService implements CrudService<CancionDTO, Integer> {

    @Autowired
    private CancionRepository cancionRepo;
    @Autowired
    private ArtistaRepository artistaRepo;

    private CancionMapper mapper = CancionMapper.INSTANCE;
    /*private CancionDTO toDto(Cancion c) {
        CancionDTO result = CancionDTO.builder()
                .id(c.getId())
                .nombre(c.getTitulo())
                .año(c.getFecha())
                .compositor(c.getArtista())
                .duracion(c.getDuracionSegundos()).build();
        return result;
        /*return CancionDTO
                .builder()
                .nombre(c.getTitulo())
                .compositor(c.getArtista())
                .año(c.getFecha().getYear())
                .duracion(c.getDuracionSegundos()).build();
    }

    private Cancion fromDto(CancionDTO c) {
        Cancion cancion = new Cancion();
        cancion.setId(c.getId());
        cancion.setTitulo(c.getNombre());
        cancion.setArtista(c.getCompositor());
        cancion.setDuracionSegundos(c.getDuracion());
        cancion.setFecha(c.getAño());
        return cancion;
/*        return Cancion
                .builder()
                .titulo(c.getNombre())
                .artista(c.getCompositor())
                .fecha(LocalDate.of(c.getAño(), Month.JANUARY, 1))
                .duracionSegundos(c.getDuracion())
                .build();
    }*/

    @Override
    public List<CancionDTO> recuperarTodos() {
        //Mapeo...
        return this.cancionRepo.findAll().stream().map(mapper::toDto).toList();
    }

    @Override
    public CancionDTO recuperarPorId(Integer id) {
        return mapper.toDto(this.cancionRepo.findById(id).orElseThrow());
    }

    public List<CancionDTO> recuperarCancionesMenosDosMinutos(){
        //return this.cancionRepo.cancionesMenosDosMinutos().stream().map(mapper::toDto).toList();
        return this.cancionRepo.cancionesMenosDosMinutos().stream().map(mapper::toDto).toList();
    }

    public List<CancionDTO> recuperarCancionesMenosDe(int maxDuracion){
        return this.cancionRepo.cancionesMenosDuracionQue(maxDuracion).stream().map(mapper::toDto).toList();
    }

    @Override
    public CancionDTO registrarNuevo(CancionDTO nuevo) {

        //Validar que no este repetido
        if (this.cancionRepo.findAll()
                .stream()
                .anyMatch(c ->
                        c.getTitulo().equals(nuevo.getNombre().trim())
                        && c.getArtista().getNombre().equals(nuevo.getCompositor().trim()))){

            throw new ServiceValidationException(
                    MessageFormat.format(
                    "Ya existe a cancion {0} de {1}",
                            nuevo.getNombre(),
                            nuevo.getCompositor()));
        }

        Cancion nueva = mapper.fromDto(nuevo);
        this.artistaRepo.save(nueva.getArtista());
        Cancion registrado = this.cancionRepo.save(nueva);
        return mapper.toDto(registrado);
    }

    @Override
    public CancionDTO actualizar(CancionDTO existente) {
        this.cancionRepo.save(mapper.fromDto(existente));
        return existente;
    }

    @Override
    public void borrar(Integer id) {
        this.cancionRepo.deleteById(id);
    }

    public void puntuarCancion(int id, int puntuacion ) {
        Cancion cancion = this.cancionRepo.findById(id).orElseThrow();

        cancion.getPuntuaciones().add(
                Puntuacion
                        .builder()
                        .valor(puntuacion)
                        .cancion(cancion)
                        .build());

        System.out.println(cancion); //Stack Overflow
        this.cancionRepo.save(cancion);
    }
}
