package repository;

import entites.Estudiante;
import factory.MySQLFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import java.util.List;

public class DaoImplEstudiante implements DaoEstudiante {

    private EntityManager em;

    public DaoImplEstudiante() {
        this.em = MySQLFactory.getInstance().getEntityManager();
    }

    @Override
    public void insertar(Estudiante estudiante) {
        em.getTransaction().begin();
        em.persist(estudiante);
        em.getTransaction().commit();
    }

    @Override
    public List<Estudiante> getEstudiantesOrderedByApellido() {
        return em.createQuery("SELECT e FROM Estudiante e ORDER BY e.apellido", Estudiante.class)
                .getResultList();
    }

    public Estudiante getEstudianteByLibreta(int libreta) {
        try {
            return em.createQuery(
                            "SELECT e FROM Estudiante e WHERE e.LU = :libreta",
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
