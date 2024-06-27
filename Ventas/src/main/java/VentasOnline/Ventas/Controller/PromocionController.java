package VentasOnline.Ventas.Controller;
import VentasOnline.Ventas.Entity.Promocion;
import VentasOnline.Ventas.Service.PromocionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/promociones")
public class PromocionController {
    @Autowired
    private PromocionService promocionService;

    @GetMapping
    public List<Promocion> obtenerTodasLasPromociones() {
        return promocionService.obtenerTodasLasPromociones();
    }

    @GetMapping("/{id}")
    public Promocion obtenerPromocionPorId(@PathVariable Long id) {
        return promocionService.obtenerPromocionPorId(id);
    }

    @PostMapping
    public Promocion crearPromocion(@RequestBody Promocion promocion) {
        return promocionService.guardarPromocion(promocion);
    }

    @PutMapping("/{id}")
    public Promocion actualizarPromocion(@PathVariable Long id, @RequestBody Promocion promocion) {
        promocion.setId(id);
        return promocionService.actualizarPromocion(promocion);
    }

    @DeleteMapping("/{id}")
    public void eliminarPromocionPorId(@PathVariable Long id) {
        promocionService.eliminarPromocionPorId(id);
    }
}