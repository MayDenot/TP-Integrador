package entites;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Estudiante_Carrera_PK {

  private Integer id_estudiante;
  private Integer id_carrera;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Estudiante_Carrera_PK that = (Estudiante_Carrera_PK) o;
    return Objects.equals(id_estudiante, that.id_estudiante) &&
            Objects.equals(id_carrera, that.id_carrera);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id_estudiante, id_carrera);
  }
}
