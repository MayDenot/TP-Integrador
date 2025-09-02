package DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Factura_ProductoDTO {
  private Integer cantidad;
  private String nombre;
  private Float valor;
}
