package entites;

import jakarta.persistence.Embeddable;

@Embeddable
public class Estudiante_Carrera_PK {
  private int idEstudiante;
  private int idCarrera;
}
