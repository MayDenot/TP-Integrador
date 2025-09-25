package repository;

import entites.Carrera;
import entites.Estudiante;

import java.time.LocalDate;

public interface DaoEstudianteCarrera {
    public void matricularEstudiante(Estudiante estudiante, Carrera carrera, LocalDate fecha, boolean graduado, int antiguedad);
}
