package entites;

import jakarta.persistence.*;
import lombok.*;

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
  @MapsId("id_estudiante")
  @JoinColumn(name = "id_estudiante")
  private Estudiante estudiante;
  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("id_carrera")
  @JoinColumn(name = "id_carrera")
  private Carrera carrera;
}
