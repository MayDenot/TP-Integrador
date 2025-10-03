package repository;

import DTO.CarreraDTO;
import DTO.ReporteDTO;
import com.sun.source.tree.TryTree;
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
    public List<CarreraDTO> getCarrerasXCantidadDeInscriptos() {
        try {
            return em.createQuery(
                    "SELECT new DTO.CarreraDTO(c.carrera, COUNT(ec.estudiante)) " +
                            "FROM Estudiante_Carrera ec " +
                            "JOIN ec.carrera c " +
                            "GROUP BY c.carrera " +
                            "ORDER BY COUNT(ec.estudiante) DESC",
                    CarreraDTO.class
            ).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener carreras por cantidad de inscriptos", e);
        }
    }
    @Override
    public Carrera getCarreraById(int id) {
        try{
            return em.find(Carrera.class, id);
        }catch(Exception e){
            return null;
        }
    }

    @Override
    public List<ReporteDTO> getReporte() {
        try {
           return em.createQuery("SELECT new DTO.ReporteDTO (" +
                                            "c.carrera, " +
                                            "COUNT(ec), " +
                                            "SUM(CASE WHEN ec.anioGraduacion > 0 THEN 1 ELSE 0 END), " +
                                            "ec.anioInscripcion" +
                                            ") " +
                                            "FROM Estudiante_Carrera ec JOIN ec.carrera c " +
                                            "GROUP BY c.carrera, ec.anioInscripcion " +
                                            "ORDER BY c.carrera ASC, ec.anioInscripcion ASC",
                                    ReporteDTO.class
                                    ).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener reporte de carreras", e);
        }
    }
}
