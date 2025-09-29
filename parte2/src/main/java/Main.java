import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import repository.DaoImplCarrera;
import utils.CargadorDatos;

public class Main {
    public static void main(String[] args) {
        DaoImplCarrera daoImplCarrera = new DaoImplCarrera();

        CargadorDatos cargadorDatos = new CargadorDatos();
//        cargadorDatos.cargarTodo();

    }
}
