package repository;

import entites.Estudiante;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

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
        return em.createQuery("SELECT e FROM Estudiante e", Estudiante.class)
                .getResultList();
    }

    public Estudiante getEstudianteByLibreta(int libreta) {
        try {
            return em.createQuery(
                            "SELECT e FROM Estudiante e WHERE e.nroLU = :libreta",
                            Estudiante.class)
                    .setParameter("libreta", libreta)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new RuntimeException("No existe estudiante con libreta " + libreta);
        }
    }

    @Override
    public List getEstudiantesByGenero(String genero) {
        try{
            return  em.createQuery("SELECT e FROM Estudiante e where e.genero = :genero ").setParameter("genero", genero).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("No existe estudiante con este genero " + genero);

        }
    }

    @Override
    public List getEstudiantesByCarreraYCiudad(int idCarrera, String ciudad) {
        try {
            return em.createQuery("SELECT ec.estudiante FROM Estudiante_Carrera  ec where ec.carrera.idCarrera=:idCarrera AND ec.estudiante.ciudad=:ciudad")
                    .setParameter(idCarrera, idCarrera).setParameter("ciudad", ciudad).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("No hay estudiantes con esas " + idCarrera + " " + ciudad);
        }
    }
}
