package ventaproducto.ventasproductos.api.Cliente;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ventaproducto.ventasproductos.dto.Cliente.ClienteDtoSave;
import ventaproducto.ventasproductos.servicies.Cliente.ClienteServiceInterface;


@RestController
@RequestMapping("/api/v1/customers")
public class ClienteController {

    private ClienteServiceInterface clienteService;

    
    public ClienteController(ClienteServiceInterface clienteService) {
        this.clienteService = clienteService;
    }

    // Crear un nuevo cliente
    @PostMapping
    public ResponseEntity<Integer> createCliente(@RequestBody ClienteDtoSave clienteJSON) {
        clienteService.guardarCliente(clienteJSON);
        return ResponseEntity.ok(200);
    }
    /* 
    // Obtener cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> getClienteById(@PathVariable Long id) {
        ClienteDto cliente = clienteService.obtenerClientePorId(id);
        return ResponseEntity.ok(cliente);
    }

    // Obtener todos los clientes
    @GetMapping
    public ResponseEntity<List<ClienteDto>> getAllClientes() {
        List<ClienteDto> clientes = clienteService.encontrarTodosClientes();
        return ResponseEntity.ok(clientes);
    }

    // Obtener cliente por email
    @GetMapping("/email/{email}")
    public ResponseEntity<List<ClienteDto>> getClientesByEmail(@PathVariable String email) {
        List<ClienteDto> clientes = clienteService.encontrarClientesPorEmail(email);
        return ResponseEntity.ok(clientes);
    }

    // Obtener clientes por dirección
    @GetMapping("/city")
    public ResponseEntity<List<ClienteDto>> getClientesByCity(@RequestParam String direccion) {
        List<ClienteDto> clientes = clienteService.encontrarClientesPorDireccion(direccion);
        return ResponseEntity.ok(clientes);
    }

    

    // Actualizar cliente por ID
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> updateCliente(@PathVariable Long id, @RequestBody ClienteDtoSave clienteDto) {
        ClienteDto updatedCliente = clienteService.actualizarCliente(id, clienteDto);
        return ResponseEntity.ok(updatedCliente);
    }

    // Eliminar cliente por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }*/
}

