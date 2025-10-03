package entites;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Estudiante {
  @Id
  @Column(name = "id_estudiante")
  private int DNI;
  @Column
  private String nombre;
  @Column
  private String apellido;
  @Column
  private int edad;
  @Column
  private String genero;
  @Column
  private String ciudad;
  @Column
  private int LU;
  @OneToMany(mappedBy = "estudiante", fetch = FetchType.LAZY)
  private List<Estudiante_Carrera> inscripciones;
}
