package repository;

import DTO.CarreraDTO;
import entites.Carrera;

import java.util.List;

public interface DaoCarrera {

    public abstract void insertar(Carrera carrera);
    public abstract List<CarreraDTO> getCarrerasXCantidadDeInscriptos();

}
