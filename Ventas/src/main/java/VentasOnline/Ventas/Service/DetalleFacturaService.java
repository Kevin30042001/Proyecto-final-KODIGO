
package VentasOnline.Ventas.Service;
import VentasOnline.Ventas.DTO.DetalleFacturaDTO;
import VentasOnline.Ventas.Entity.DetalleFactura;
import java.util.List;

public interface DetalleFacturaService {
    List<DetalleFactura> obtenerTodosLosDetallesFactura();
    List<DetalleFacturaDTO> obtenerTodosLosDetallesFacturaDTO();
    DetalleFacturaDTO obtenerDetalleFacturaPorId(Long id);
    DetalleFactura guardarDetalleFactura(DetalleFactura detalleFactura);
    DetalleFactura actualizarDetalleFactura(DetalleFactura detalleFactura);
    void eliminarDetalleFacturaPorId(Long id);

}