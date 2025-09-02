package entities;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Factura_Producto {
    private Integer idFactura;
    private Integer idProducto;
    private Integer cantidad;
}
