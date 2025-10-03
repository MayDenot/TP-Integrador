package entites;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Carrera {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO) // sino IDENTITY
  private int idCarrera;
  @Column
  private String carrera;
  @Column
  private int duracion;
  @OneToMany(mappedBy = "carrera", fetch = FetchType.LAZY)
  private List<Estudiante_Carrera> inscripciones;
}
