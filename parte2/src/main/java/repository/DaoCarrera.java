package repository;

import DTO.CarreraDTO;
import DTO.ReporteDTO;
import entites.Carrera;

import java.util.List;

public interface DaoCarrera {

    public abstract void insertar(Carrera carrera);
    public abstract List<CarreraDTO> getCarrerasXCantidadDeInscriptos();
    public abstract Carrera getCarreraById(int id);
    public abstract List<ReporteDTO> getReporte();

}
