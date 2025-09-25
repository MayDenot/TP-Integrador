package factory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MySQLFactory extends Factory {


    private static EntityManagerFactory emf;
    private static EntityManager em;

    static {
        emf = Persistence.createEntityManagerFactory("parte2");
        em = emf.createEntityManager();
    }


}
