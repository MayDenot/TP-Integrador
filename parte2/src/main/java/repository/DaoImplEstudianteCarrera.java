package repository;

import entites.Carrera;
import entites.Estudiante;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;

public class DaoImplEstudianteCarrera implements DaoEstudianteCarrera{
    private EntityManager em;

    public DaoImplEstudianteCarrera(EntityManager em) {
        this.em = em;
    }

    @Override
    public void matricularEstudiante(Estudiante estudiante, Carrera carrera, LocalDate fecha, boolean graduado, int antiguedad) {

    }
}
