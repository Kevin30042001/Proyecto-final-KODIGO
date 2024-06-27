package VentasOnline.Ventas.Service;
import VentasOnline.Ventas.Entity.Promocion;
import java.util.List;

public interface PromocionService {
    List<Promocion> obtenerTodasLasPromociones();
    Promocion obtenerPromocionPorId(Long id);
    Promocion guardarPromocion(Promocion promocion);
    Promocion actualizarPromocion(Promocion promocion);
    void eliminarPromocionPorId(Long id);
}
