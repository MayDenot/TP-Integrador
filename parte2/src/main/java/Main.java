import factory.Factory;
import factory.MySQLFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import repository.DaoImplCarrera;
import utils.CargadorDatos;

public class Main {
    public static void main(String[] args) {
       MySQLFactory factory = (MySQLFactory) Factory.getAbstractFactory(1);

        DaoImplCarrera daoImplCarrera = new DaoImplCarrera();

        CargadorDatos cargadorDatos = new CargadorDatos();
        cargadorDatos.cargarTodo();

    }
}
