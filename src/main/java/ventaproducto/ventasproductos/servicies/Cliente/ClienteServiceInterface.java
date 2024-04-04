package ventaproducto.ventasproductos.servicies.Cliente;

import java.util.List;
import java.util.Optional;

import ventaproducto.ventasproductos.dto.Cliente.ClienteDto;
import ventaproducto.ventasproductos.dto.Cliente.ClienteDtoSave;

public interface ClienteServiceInterface {

    ClienteDto guardarCliente(ClienteDtoSave cliente);
    
    ClienteDto actualizarCliente(ClienteDtoSave clienteDto);

    Optional<List<ClienteDto>> getAllClientes();
    
    ClienteDto findById(Long id) throws RuntimeException;

    void eliminarCliente(Long id) throws RuntimeException;
    
    Optional<ClienteDto> findByEmail(String email);

    Optional<List<ClienteDto>> findByDireccion(String direccion);

    Optional<List<ClienteDto>> findByNombreStartingWith(String nombre);
    
}