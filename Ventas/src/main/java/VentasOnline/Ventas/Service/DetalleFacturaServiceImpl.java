package VentasOnline.Ventas.Service;

import VentasOnline.Ventas.DTO.DetalleFacturaDTO;
import VentasOnline.Ventas.Entity.DetalleFactura;

import VentasOnline.Ventas.Repository.DetalleFacturaRepository;
import VentasOnline.Ventas.Repository.ProductoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetalleFacturaServiceImpl implements DetalleFacturaService {

    @Autowired
    private DetalleFacturaRepository detalleFacturaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<DetalleFactura> obtenerTodosLosDetallesFactura() {
        return detalleFacturaRepository.findAll();
    }

    @Override
    public List<DetalleFacturaDTO> obtenerTodosLosDetallesFacturaDTO() {
        List<DetalleFactura> detalles = detalleFacturaRepository.findAll();
        return detalles.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public DetalleFacturaDTO obtenerDetalleFacturaPorId(Long id) {
        DetalleFactura detalleFactura = detalleFacturaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Detalle de factura no encontrado con id: " + id));
        return convertToDTO(detalleFactura);
    }


    @Override
    public DetalleFactura guardarDetalleFactura(DetalleFactura detalleFactura) {
        return detalleFacturaRepository.save(detalleFactura);
    }

    @Override
    public DetalleFactura actualizarDetalleFactura(DetalleFactura detalleFactura) {
        return detalleFacturaRepository.save(detalleFactura);
    }

    @Override
    public void eliminarDetalleFacturaPorId(Long id) {
        detalleFacturaRepository.deleteById(id);
    }

    private DetalleFacturaDTO convertToDTO(DetalleFactura detalleFactura) {
        DetalleFacturaDTO dto = new DetalleFacturaDTO();
        dto.setId(detalleFactura.getId());
        dto.setCantidad(detalleFactura.getCantidad());
        dto.setPrecioUnitario(detalleFactura.getPrecio_unitario());
        dto.setFacturaId(detalleFactura.getFactura().getId());

        if (detalleFactura.getProducto() != null) {
            dto.setProductoId(detalleFactura.getProducto().getId());
            dto.setProductoNombre(detalleFactura.getProducto().getNombre());
        }

        return dto;
    }
}