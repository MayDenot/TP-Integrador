package DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReporteDTO {
    private String nombreCarrera;
    private Long cantidadInscriptos;
    private Long cantidadGradudados;
    private Integer anio;
}
