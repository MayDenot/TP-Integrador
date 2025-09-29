package entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
