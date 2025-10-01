package repository;

import DTO.CarreraDTO;
import entites.Carrera;
import factory.MySQLFactory;
import jakarta.persistence.EntityManager;

import java.util.List;

public class DaoImplCarrera implements DaoCarrera{
    private EntityManager em;

    public DaoImplCarrera() {
        this.em =  MySQLFactory.getInstance().getEntityManager();
    }

    @Override
    public void insertar(Carrera carrera) {
        em.getTransaction().begin();
        em.persist(carrera);
        em.getTransaction().commit();
    }
    @Override
    public List<CarreraDTO> getCarrerasXCantidadDeInscriptos() {
        try {
            return em.createQuery(
                    "SELECT new DTO.CarreraDTO(c.carrera, COUNT(ec.estudiante)) " +
                            "FROM Estudiante_Carrera ec " +
                            "JOIN ec.carrera c " +
                            "GROUP BY c.carrera " +
                            "ORDER BY COUNT(ec.estudiante) DESC",
                    CarreraDTO.class
            ).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener carreras por cantidad de inscriptos", e);
        }
    }
}
