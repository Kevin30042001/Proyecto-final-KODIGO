package VentasOnline.Ventas.DTO;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class FacturaDTO {
    private Long id;
    private String clienteNombre;
    private LocalDate fecha;
    private Double total;
    private List<DetalleFacturaDTO> detalles;
}