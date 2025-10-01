package repository;

import entites.Carrera;
import entites.Estudiante;
import entites.Estudiante_Carrera;

import java.time.LocalDate;

public interface DaoEstudianteCarrera {
    public void insertEstudianteACarrera(Estudiante_Carrera EC);
}
