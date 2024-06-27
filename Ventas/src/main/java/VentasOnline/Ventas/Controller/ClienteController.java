package VentasOnline.Ventas.Controller;
import VentasOnline.Ventas.Entity.Cliente;
import VentasOnline.Ventas.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    // Este controlador maneja las operaciones CRUD para la entidad Cliente
    @Autowired
    private ClienteService clienteService;

    //obtiene todos los ciientes
    @GetMapping
    public List<Cliente> obtenerTodosLosClientes() {
        return clienteService.obtenerTodosLosClientes();
    }

    //Obtiene el cliente por id
    @GetMapping("/{id}")
    public Cliente obtenerClientePorId(@PathVariable Long id) {
        return clienteService.obtenerClientePorId(id);
    }

    //Crea un nuevo cliente
    @PostMapping
    public Cliente crearCliente(@RequestBody Cliente cliente) {
        return clienteService.guardarCliente(cliente);
    }

    //Actualiza un cleinte existente por id
    @PutMapping("/{id}")
    public Cliente actualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        cliente.setId(id);
        return clienteService.actualizarCliente(cliente);
    }

    //Elimina un cliente existente por id
    @DeleteMapping("/{id}")
    public void eliminarClientePorId(@PathVariable Long id) {
        clienteService.eliminarClientePorId(id);
    }
}