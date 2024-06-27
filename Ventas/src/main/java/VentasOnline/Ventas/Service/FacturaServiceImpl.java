package VentasOnline.Ventas.Service;

import VentasOnline.Ventas.DTO.FacturaDTO;
import VentasOnline.Ventas.DTO.DetalleFacturaDTO;
import VentasOnline.Ventas.Entity.Factura;
import VentasOnline.Ventas.Entity.DetalleFactura;
import VentasOnline.Ventas.Entity.Cliente;
import VentasOnline.Ventas.Entity.Producto;
import VentasOnline.Ventas.Repository.FacturaRepository;
import VentasOnline.Ventas.Repository.ClienteRepository;
import VentasOnline.Ventas.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacturaServiceImpl implements FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<FacturaDTO> obtenerTodasLasFacturas() {
        List<Factura> facturas = facturaRepository.findAll();
        return facturas.stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    @Override
    public FacturaDTO obtenerFacturaPorId(Long id) {
        Factura factura = facturaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Factura no encontrada con id: " + id));
        return convertirADTO(factura);
    }

    @Override
    @Transactional
    public FacturaDTO crearFactura(Factura factura) {
        Cliente cliente = clienteRepository.findById(factura.getCliente().getId())
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado"));
        factura.setCliente(cliente);

        for (DetalleFactura detalle : factura.getDetalles()) {
            detalle.setFactura(factura);
            if (detalle.getProducto() != null) {
                Producto producto = productoRepository.findById(detalle.getProducto().getId())
                        .orElse(null);
                detalle.setProducto(producto);
            }
        }

        Factura nuevaFactura = facturaRepository.save(factura);
        return convertirADTO(nuevaFactura);
    }

    @Override
    @Transactional
    public FacturaDTO actualizarFactura(Long id, Factura facturaActualizada) {
        Factura factura = facturaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Factura no encontrada con id: " + id));

        // Actualizar el cliente
        if (facturaActualizada.getCliente() != null && facturaActualizada.getCliente().getId() != null) {
            Cliente cliente = clienteRepository.findById(facturaActualizada.getCliente().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con id: " + facturaActualizada.getCliente().getId()));
            factura.setCliente(cliente);
        }

        factura.setFecha(facturaActualizada.getFecha());
        factura.setTotal(facturaActualizada.getTotal());

        // Eliminar detalles que ya no están presentes
        factura.getDetalles().removeIf(detalle ->
                facturaActualizada.getDetalles().stream()
                        .noneMatch(d -> d.getId() != null && d.getId().equals(detalle.getId())));

        // Actualizar o agregar nuevos detalles
        for (DetalleFactura detalleActualizado : facturaActualizada.getDetalles()) {
            if (detalleActualizado.getId() != null) {
                // Actualizar detalle existente
                DetalleFactura detalleExistente = factura.getDetalles().stream()
                        .filter(d -> d.getId().equals(detalleActualizado.getId()))
                        .findFirst()
                        .orElseThrow(() -> new EntityNotFoundException("Detalle de factura no encontrado"));
                detalleExistente.setCantidad(detalleActualizado.getCantidad());
                detalleExistente.setPrecio_unitario(detalleActualizado.getPrecio_unitario());
                if (detalleActualizado.getProducto() != null) {
                    Producto producto = productoRepository.findById(detalleActualizado.getProducto().getId())
                            .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con id: " + detalleActualizado.getProducto().getId()));
                    detalleExistente.setProducto(producto);
                }
            } else {
                // Agregar nuevo detalle
                detalleActualizado.setFactura(factura);
                if (detalleActualizado.getProducto() != null) {
                    Producto producto = productoRepository.findById(detalleActualizado.getProducto().getId())
                            .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con id: " + detalleActualizado.getProducto().getId()));
                    detalleActualizado.setProducto(producto);
                }
                factura.getDetalles().add(detalleActualizado);
            }
        }

        Factura facturaGuardada = facturaRepository.save(factura);
        return convertirADTO(facturaGuardada);
    }


    @Override
    public boolean eliminarFactura(Long id) {
        if (facturaRepository.existsById(id)) {
            facturaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private FacturaDTO convertirADTO(Factura factura) {
        FacturaDTO dto = new FacturaDTO();
        dto.setId(factura.getId());
        dto.setClienteNombre(factura.getCliente() != null ? factura.getCliente().getNombre() : "Cliente no especificado");
        dto.setFecha(factura.getFecha());
        dto.setTotal(factura.getTotal());
        dto.setDetalles(factura.getDetalles().stream()
                .map(this::convertirDetalleADTO)
                .collect(Collectors.toList()));
        return dto;
    }

    private DetalleFacturaDTO convertirDetalleADTO(DetalleFactura detalle) {
        DetalleFacturaDTO dto = new DetalleFacturaDTO();
        dto.setId(detalle.getId());
        dto.setProductoNombre(detalle.getProducto() != null ? detalle.getProducto().getNombre() : "Producto eliminado");
        dto.setCantidad(detalle.getCantidad());
        dto.setPrecioUnitario(detalle.getPrecio_unitario());
        dto.setFacturaId(detalle.getFactura().getId());
        dto.setProductoId(detalle.getProducto() != null ? detalle.getProducto().getId() : null);
        return dto;
    }
}