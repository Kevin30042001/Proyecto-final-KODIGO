package VentasOnline.Ventas.Service;
import VentasOnline.Ventas.Entity.Cliente;
import java.util.List;

public interface ClienteService {
    List<Cliente> obtenerTodosLosClientes();
    Cliente obtenerClientePorId(Long id);
    Cliente guardarCliente(Cliente cliente);
    Cliente actualizarCliente(Cliente cliente);
    void eliminarClientePorId(Long id);
}

