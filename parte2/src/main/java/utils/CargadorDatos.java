package utils;

import entites.Carrera;
import entites.Estudiante;
import entites.Estudiante_Carrera;
import factory.MySQLFactory;
import jakarta.persistence.EntityManager;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import repository.DaoEstudiante;

import java.io.FileReader;
import java.io.IOException;
import java.util.function.Function;

public class CargadorDatos {

    private EntityManager em;

    public CargadorDatos() {
        this.em =  MySQLFactory.getInstance().getEntityManager();
    }
    private static <T> void cargarDesdeCSV(String rutaCSV, EntityManager em, Function<CSVRecord, T> mapper) {
        try (
                FileReader reader = new FileReader(rutaCSV);
                CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(reader)
        ) {
            em.getTransaction().begin();

            for (CSVRecord row : parser) {
                T entidad = mapper.apply(row);
                em.persist(entidad);
            }

            em.getTransaction().commit();
            System.out.println("Datos cargados correctamente desde: " + rutaCSV);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al leer el archivo CSV: " + rutaCSV, e);
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        }
    }

    // ==============================
    // CARGAR ESTUDIANTES
    // ==============================
    public void addEstudiantes() {
        cargarDesdeCSV("parte2/src/main/resources/csv/estudiantes.csv", this.em, row -> {
            Estudiante estudiante = new Estudiante();
            estudiante.setIdEstudiante(Integer.parseInt(row.get("idEstudiante"))); // si no es autogenerado
            estudiante.setNombre(row.get("nombre"));
            estudiante.setApellido(row.get("apellido"));
            estudiante.setEdad(Integer.parseInt(row.get("edad")));
            estudiante.setGenero(row.get("genero"));
            estudiante.setDni(Integer.parseInt(row.get("dni")));
            estudiante.setCiudad(row.get("ciudad"));
            estudiante.setNroLU(Integer.parseInt(row.get("nroLu")));
            return estudiante;
        });
    }

    // ==============================
    // CARGAR CARRERAS
    // ==============================
    public void addCarreras() {
        cargarDesdeCSV("parte2/src/main/resources/csv/carreras.csv", this.em, row -> {
            Carrera carrera = new Carrera();
            carrera.setIdCarrera(Integer.parseInt(row.get("idCarrera")));
            carrera.setCarrera(row.get("carrera"));
            carrera.setDuracion(Integer.parseInt(row.get("duracion")));
            return carrera;
        });
    }

    // ==============================
    // CARGAR RELACIÓN ESTUDIANTE-CARRERA
    // ==============================
    public  void addEstudiantesCarrera() {
        cargarDesdeCSV("parte2/src/main/resources/csv/estudianteCarrera.csv", this.em, row -> {
            Estudiante_Carrera ec = new Estudiante_Carrera();

            int idEstudiante = Integer.parseInt(row.get("idEstudiante"));
            int idCarrera = Integer.parseInt(row.get("idCarrera"));

            // Usamos getReference para no cargar toda la entidad
            Estudiante estudianteRef = this.em.getReference(Estudiante.class, idEstudiante);
            Carrera carreraRef = this.em.getReference(Carrera.class, idCarrera);

            ec.setEstudiante(estudianteRef);
            ec.setCarrera(carreraRef);
            ec.setAnioInscripcion(Integer.parseInt(row.get("anioInscripçion")));
            ec.setAnioGraduacion(Integer.parseInt(row.get("anioGraduacion")));
            ec.setAntiguedad(Integer.parseInt(row.get("antiguedad")));


            return ec;
        });
    }

    // ==============================
    // MÉT0DO PRINCIPAL PARA CARGAR TOD0
    // ==============================
    public void cargarTodo() {
        System.out.println("Iniciando carga de datos iniciales...");

        addCarreras();             // Primero carreras
        addEstudiantes();          // Luego estudiantes
        addEstudiantesCarrera();   // Finalmente la relación

        System.out.println("Carga inicial finalizada correctamente.");
    }
}


