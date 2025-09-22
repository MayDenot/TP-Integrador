package entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Estudiante {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO) // sino IDENTITY
  private int idEstudiante;
  @Column
  private String nombre;
  @Column
  private String apellido;
  @Column
  private int edad;
  @Column
  private String genero;
  @Column
  private int dni;
  @Column
  private String ciudad;
  @Column
  private int nroLU;
  @OneToMany(mappedBy = "estudiante", fetch = FetchType.LAZY)
  private List<Estudiante_Carrera> inscripciones;
}
