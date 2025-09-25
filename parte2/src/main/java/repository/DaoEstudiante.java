package repository;

import entites.Carrera;
import entites.Estudiante;

import java.util.List;

public interface DaoEstudiante {

    public abstract void insertar(Estudiante estudiante);
    public abstract List<Estudiante> getEstudiantes();// preguntar por filtro;
    public abstract Estudiante getEstudianteByLibreta(int libreta);
    public abstract List<Estudiante> getEstudiantesByGenero(String genero);
    public abstract List<Estudiante> getEstudiantesByCarreraYCiudad(int id_carrera, String ciudad);

}
