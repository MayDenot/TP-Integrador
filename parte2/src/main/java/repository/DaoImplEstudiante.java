package repository;

import entites.Estudiante;
import jakarta.persistence.EntityManager;

import java.util.List;

public class DaoImplEstudiante implements DaoEstudiante {

    private EntityManager em;

    public DaoImplEstudiante(EntityManager em) {
        this.em = em;
    }

    @Override
    public void insertar(Estudiante estudiante) {

    }

    @Override
    public List<Estudiante> getEstudiantes() {
        return List.of();
    }

    @Override
    public Estudiante getEstudianteByLibreta(int libreta) {
        return null;
    }

    @Override
    public List<Estudiante> getEstudiantesByGenero(String genero) {
        return List.of();
    }

    @Override
    public List<Estudiante> getEstudiantesByCarreraYCiudad(int id_carrera, String ciudad) {
        return List.of();
    }
}
