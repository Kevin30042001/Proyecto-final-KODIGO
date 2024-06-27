package VentasOnline.Ventas.DTO;

import lombok.Data;

@Data
public class DetalleFacturaDTO {
    private Long id;
    private Integer cantidad;
    private Double precioUnitario;
    private Long facturaId;
    private Long productoId;
    private String productoNombre;
}