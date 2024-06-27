package VentasOnline.Ventas.Service;

import VentasOnline.Ventas.DTO.FacturaDTO;
import VentasOnline.Ventas.Entity.Factura;
import java.util.List;

public interface FacturaService {
    List<FacturaDTO> obtenerTodasLasFacturas();
    FacturaDTO obtenerFacturaPorId(Long id);
    FacturaDTO crearFactura(Factura factura);
    FacturaDTO actualizarFactura(Long id, Factura factura);
    boolean eliminarFactura(Long id);
}