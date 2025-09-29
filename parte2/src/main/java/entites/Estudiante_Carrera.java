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
  private int anioInscripcion;
  @Column
  private int antiguedad;
  @Column
  private int anioGraduacion;
  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("idEstudiante")
  @JoinColumn(name = "idEstudiante")
  private Estudiante estudiante;
  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("idCarrera")
  @JoinColumn(name = "idCarrera")
  private Carrera carrera;
}
