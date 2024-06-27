package VentasOnline.Ventas.Controller;

import VentasOnline.Ventas.DTO.FacturaDTO;
import VentasOnline.Ventas.Entity.Factura;
import VentasOnline.Ventas.Service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/facturas")
public class FacturaController {
    @Autowired
    private FacturaService facturaService;

    @GetMapping
    public ResponseEntity<List<FacturaDTO>> obtenerTodasLasFacturas() {
        List<FacturaDTO> facturas = facturaService.obtenerTodasLasFacturas();
        return new ResponseEntity<>(facturas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacturaDTO> obtenerFacturaPorId(@PathVariable Long id) {
        FacturaDTO factura = facturaService.obtenerFacturaPorId(id);
        return factura != null ?
                new ResponseEntity<>(factura, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<FacturaDTO> crearFactura(@RequestBody Factura factura) {
        FacturaDTO nuevaFactura = facturaService.crearFactura(factura);
        return new ResponseEntity<>(nuevaFactura, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FacturaDTO> actualizarFactura(@PathVariable Long id, @RequestBody Factura factura) {
        FacturaDTO facturaActualizada = facturaService.actualizarFactura(id, factura);
        if (facturaActualizada != null) {
            return new ResponseEntity<>(facturaActualizada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarFactura(@PathVariable Long id) {
        boolean eliminado = facturaService.eliminarFactura(id);
        return eliminado ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}