package ventaproducto.ventasproductos.api.cliente;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ventaproducto.ventasproductos.dto.Cliente.ClienteDto;
import ventaproducto.ventasproductos.dto.Cliente.ClienteDtoSave;
import ventaproducto.ventasproductos.servicies.Cliente.ClienteServiceInterface;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/v1/customers")
public class ClienteController {

    private ClienteServiceInterface clienteService;

    
    public ClienteController(ClienteServiceInterface clienteService) {
        this.clienteService = clienteService;
    }

    // Crear un nuevo cliente
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Integer> createCliente(@RequestBody ClienteDtoSave clienteJSON) {
        clienteService.guardarCliente(clienteJSON);
        return ResponseEntity.ok().build();
    }
   
    // Obtener cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> getClienteById(@PathVariable("id") Long id) {
        ClienteDto cliente = clienteService.findById(id);
        return ResponseEntity.ok(cliente);
    }

    // Obtener todos los clientes
    @GetMapping
    public ResponseEntity<Optional<List<ClienteDto>>> getAllClientes() {
        Optional<List<ClienteDto>> clientes = clienteService.getAllClientes();
        return ResponseEntity.ok(clientes);
    }
  
    // Obtener cliente por email
    @GetMapping("/email/{email}")
    public ResponseEntity<Optional<ClienteDto>> getClientesByEmail(@PathVariable("email") String email) {
        Optional<ClienteDto> clientes = clienteService.findByEmail(email);
        return ResponseEntity.ok(clientes);
    }

    // Obtener clientes por dirección
    @GetMapping("/city")
    public ResponseEntity<Optional<List<ClienteDto>>> getClientesByCity(@RequestParam("direccion") String direccion) {
        Optional<List<ClienteDto>> clientes = clienteService.findByDireccion(direccion);
        return ResponseEntity.ok(clientes);
    }

    // Actualizar cliente por ID
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> updateCliente(@PathVariable("id") Long id, @RequestBody ClienteDtoSave clienteDto) {
        ClienteDto updatedCliente = clienteService.actualizarCliente(clienteDto);
        return ResponseEntity.ok(updatedCliente);
    }
 
    // Eliminar cliente por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable("id") Long id) {
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }
}

