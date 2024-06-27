package VentasOnline.Ventas.Controller;

import VentasOnline.Ventas.Entity.DetalleFactura;
import VentasOnline.Ventas.Service.DetalleFacturaService;
import VentasOnline.Ventas.DTO.DetalleFacturaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/detallefactura")
public class DetalleFacturaController {
    //Este controlador maneja todas las operaciones de detalle factura
    @Autowired
    private DetalleFacturaService detalleFacturaService;


    //muestra la lista de detalle factura
    @GetMapping("/simple")
    public List<DetalleFacturaDTO> obtenerTodosLosDetallesFacturaSimple() {
        return detalleFacturaService.obtenerTodosLosDetallesFacturaDTO();
    }

    //Muestra un detalle factura por su id
    @GetMapping("/simple/{id}")
    public ResponseEntity<DetalleFacturaDTO> obtenerDetalleFacturaPorId(@PathVariable Long id) {
        DetalleFacturaDTO detalleFactura = detalleFacturaService.obtenerDetalleFacturaPorId(id);
        return ResponseEntity.ok(detalleFactura);
    }


    //Crea un detalle factura
    @PostMapping
    public DetalleFactura crearDetalleFactura(@RequestBody DetalleFactura detalleFactura) {
        return detalleFacturaService.guardarDetalleFactura(detalleFactura);
    }

    //Actualiza un detalle factura por su id
    @PutMapping("/{id}")
    public DetalleFactura actualizarDetalleFactura(@PathVariable Long id, @RequestBody DetalleFactura detalleFactura) {
        detalleFactura.setId(id);
        return detalleFacturaService.actualizarDetalleFactura(detalleFactura);
    }

    //Elimina un detalle factura por su id
    @DeleteMapping("/{id}")
    public void eliminarDetalleFacturaPorId(@PathVariable Long id) {
        detalleFacturaService.eliminarDetalleFacturaPorId(id);
    }

}