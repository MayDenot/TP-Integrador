package repository;

import entites.Carrera;
import entites.Estudiante;
import entites.Estudiante_Carrera;
import factory.MySQLFactory;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;

public class DaoImplEstudianteCarrera implements DaoEstudianteCarrera{
    private EntityManager em;

    public DaoImplEstudianteCarrera() {
        this.em =  MySQLFactory.getInstance().getEntityManager();
    }

    @Override
    public void insertEstudianteACarrera(Estudiante_Carrera ec) {
        em.getTransaction().begin();
        em.persist(ec);
        em.getTransaction().commit();
    }
}
