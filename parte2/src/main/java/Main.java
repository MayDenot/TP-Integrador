import DTO.CarreraDTO;
import DTO.ReporteDTO;
import entites.Carrera;
import entites.Estudiante;
import entites.Estudiante_Carrera;
import entites.Estudiante_Carrera_PK;
import factory.Factory;
import factory.MySQLFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
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
//        CargadorDatos cargadorDatos = new CargadorDatos();
//        cargadorDatos.cargarTodo();
        Carrera carrera1 = daoImplCarrera.getCarreraById(1);
        Estudiante estudiante = new Estudiante(46116932, "Daniel", "Banegas", 21, "Male", "Olavarria", 78695, new ArrayList<Estudiante_Carrera>());// punto2.a
        //daoImplEstudiante.insertar(estudiante);// punto 2.a
        System.out.println("///////////////////////////////////////////////////////////////");
        Estudiante_Carrera_PK E_C_pk = new Estudiante_Carrera_PK(estudiante.getDNI(), carrera1.getIdCarrera());
        Estudiante_Carrera E_carrera = new Estudiante_Carrera(E_C_pk, 2022, 2, 2025, estudiante, carrera1);// punto 2.b
        //daoEstudianteCarrera.insertEstudianteACarrera(E_carrera);// punto 2.b
        System.out.println("///////////////////////////////////////////////////////////////");
        List<Estudiante> estudiantesByApellido = daoImplEstudiante.getEstudiantesOrderedByApellido();// punto2.c
        System.out.println(estudiantesByApellido);//punto 2.c
        System.out.println("///////////////////////////////////////////////////////////////");
        Estudiante e1 = daoImplEstudiante.getEstudianteByLibreta(estudiante.getLU());// punto 2.d
        System.out.println(e1);// punto 2.d
        System.out.println("///////////////////////////////////////////////////////////////");
        List<Estudiante> estudiantesByGenero = daoImplEstudiante.getEstudiantesByGenero("Female");// punto2.e
        System.out.println(estudiantesByGenero);// punto 2.e
        System.out.println("///////////////////////////////////////////////////////////////");
        List<CarreraDTO> tipo = daoImplCarrera.getCarrerasXCantidadDeInscriptos(); //punto 2.f
        System.out.println(tipo); //punto 2.f
        System.out.println("///////////////////////////////////////////////////////////////");
        List<Estudiante> estudiantesPorCyC = daoImplEstudiante.getEstudiantesByCarreraYCiudad(carrera1.getIdCarrera(), estudiante.getCiudad());// punto 2.g
        System.out.println(estudiantesPorCyC);// punto 2.g
        System.out.println("///////////////////////////////////////////////////////////////");
        List<ReporteDTO> reporte = daoImplCarrera.getReporte();
        System.out.println(reporte);
    }
}
