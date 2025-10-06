import DTO.CarreraDTO;
import DTO.ReporteDTO;
import entites.Carrera;
import entites.Estudiante;
import entites.Estudiante_Carrera;
import entites.Estudiante_Carrera_PK;
import factory.Factory;
import factory.MySQLFactory;
import repository.*;
import utils.CargadorDatos;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        MySQLFactory factory = (MySQLFactory) Factory.getAbstractFactory(1);

        DaoImplCarrera daoImplCarrera = new DaoImplCarrera();
        DaoImplEstudiante daoImplEstudiante = new DaoImplEstudiante();
        DaoImplEstudianteCarrera daoEstudianteCarrera = new DaoImplEstudianteCarrera();

        CargadorDatos cargadorDatos = new CargadorDatos();
        cargadorDatos.cargarTodo();

        Carrera carrera1 = daoImplCarrera.getCarreraById(1); //TUDAI
        Estudiante estudiante = new Estudiante(46116932, "Daniel", "Banegas", 21, "Male", "Olavarria", 78695, new ArrayList<Estudiante_Carrera>());// punto2.a

        // Punto 2-a
        daoImplEstudiante.insertar(estudiante);

        System.out.println("///////////////////////////////////////////////////////////////");

        Estudiante_Carrera_PK E_C_pk = new Estudiante_Carrera_PK(estudiante.getDNI(), carrera1.getIdCarrera());
        Estudiante_Carrera E_carrera = new Estudiante_Carrera(E_C_pk, 2022, 2, 2025, estudiante, carrera1);// punto 2.b

        // Punto 2-b
        daoEstudianteCarrera.insertEstudianteACarrera(E_carrera);

        System.out.println("///////////////////////////////////////////////////////////////");

        // Punto 2-c
        List<Estudiante> estudiantesByApellido = daoImplEstudiante.getEstudiantesOrderedByApellido();
        System.out.println(estudiantesByApellido);

        System.out.println("///////////////////////////////////////////////////////////////");

        // Punto 2-d
        Estudiante e1 = daoImplEstudiante.getEstudianteByLibreta(estudiante.getLU());
        System.out.println(e1);

        System.out.println("///////////////////////////////////////////////////////////////");

        // Punto 2-e
        List<Estudiante> estudiantesByGenero = daoImplEstudiante.getEstudiantesByGenero("Female");
        System.out.println(estudiantesByGenero);

        System.out.println("///////////////////////////////////////////////////////////////");

        // Punto 2-f
        List<CarreraDTO> tipo = daoImplCarrera.getCarrerasXCantidadDeInscriptos();
        System.out.println(tipo);

        System.out.println("///////////////////////////////////////////////////////////////");

        // Punto 2-g
        List<Estudiante> estudiantesPorCyC = daoImplEstudiante.getEstudiantesByCarreraYCiudad(carrera1.getIdCarrera(), estudiante.getCiudad());
        System.out.println(estudiantesPorCyC);

        System.out.println("///////////////////////////////////////////////////////////////");

        // Punto 3
        List<ReporteDTO> reporte = daoImplCarrera.getReporte();
        System.out.println(reporte);
    }
}
