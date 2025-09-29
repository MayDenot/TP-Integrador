package repository;

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
    public List<Carrera> getCarrerasXCantidadDeInscriptos() {
        return List.of();
    }
}
