package DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class ClienteDTO {
    private Integer idCliente;
    private String nombre;
    private Integer facturacion;
}
