package VentasOnline.Ventas.Service;

import VentasOnline.Ventas.Entity.Producto;
import java.util.List;

public interface ProductoService {
    List<Producto> obtenerTodosLosProductos();
    Producto obtenerProductoPorId(Long id);
    Producto guardarProducto(Producto producto);
    Producto actualizarProducto(Producto producto);
    void eliminarProductoPorId(Long id);
}