import DTO.CarreraDTO;
import factory.Factory;
import factory.MySQLFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import repository.DaoImplCarrera;
import repository.DaoImplEstudiante;
import utils.CargadorDatos;

import java.util.List;

public class Main {
    public static void main(String[] args) {
       MySQLFactory factory = (MySQLFactory) Factory.getAbstractFactory(1);

        DaoImplCarrera daoImplCarrera = new DaoImplCarrera();

//        CargadorDatos cargadorDatos = new CargadorDatos();
//        cargadorDatos.cargarTodo();
        List<CarreraDTO> tipo = daoImplCarrera.getCarrerasXCantidadDeInscriptos();
        System.out.println(tipo);

    }
}
