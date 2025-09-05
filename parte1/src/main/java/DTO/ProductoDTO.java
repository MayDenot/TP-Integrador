package DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductoDTO {
  private Integer idProducto;
  private String nombre;
  private int recaudacion;
}
