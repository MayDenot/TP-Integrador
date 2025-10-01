package factory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import repository.*;

public class MySQLFactory extends Factory {

    // Singleton
    private static MySQLFactory instance = null;

    private static EntityManagerFactory emf;
    private static EntityManager em;

    static {
        emf = Persistence.createEntityManagerFactory("parte2");
        em = emf.createEntityManager();
    }

    // Constructor privado para Singleton
    private MySQLFactory() {}

    //Devuelve la única instancia (Singleton).
    public static synchronized MySQLFactory getInstance() {
        if (instance == null) {
            instance = new MySQLFactory();
        }
        return instance;
    }

    //Devuelve el EntityManager compartido.
    public EntityManager getEntityManager() {
        return em;
    }


    @Override
    public DaoEstudiante getDaoEstudiante() {
        return new DaoImplEstudiante();
    }

    @Override
    public DaoCarrera getDaoCarrera() {
        return new DaoImplCarrera();
    }

    @Override
    public DaoEstudianteCarrera getDaoEstudianteCarrera() {
        return new DaoImplEstudianteCarrera();
    }



}
