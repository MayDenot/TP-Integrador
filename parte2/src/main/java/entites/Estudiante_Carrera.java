package entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Estudiante_Carrera {
  @EmbeddedId
  private Estudiante_Carrera_PK idCompuesta;
  @Column
  private LocalDate fechaInscripcion;
  @Column
  private int antiguedad;
  @Column
  private boolean graduado;
  @ManyToOne(fetch = FetchType.EAGER)
  @MapsId("idEstudiante")
  private Estudiante estudiante;
  @ManyToOne(fetch = FetchType.EAGER)
  @MapsId("idCarrera")
  private Carrera carrera;
}
