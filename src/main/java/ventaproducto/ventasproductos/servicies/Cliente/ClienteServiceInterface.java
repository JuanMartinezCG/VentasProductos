package ventaproducto.ventasproductos.servicies.Cliente;

import java.util.List;

import ventaproducto.ventasproductos.dto.Cliente.ClienteDto;
import ventaproducto.ventasproductos.dto.Cliente.ClienteDtoSave;

public interface ClienteServiceInterface {

    ClienteDto guardarCliente(ClienteDtoSave cliente);
    /*
    List<ClienteDto> encontrarTodosClientes();
    
    ClienteDto obtenerClientePorId(Long id);
    
    ClienteDto actualizarCliente(Long id, ClienteDtoSave clienteDto);
    
    void eliminarCliente(Long id);
    
    List<ClienteDto> encontrarClientesPorEmail(String email);
    
    List<ClienteDto> encontrarClientesPorDireccion(String direccion);
    
    List<ClienteDto> encontrarClientesPorNombre(String nombre);*/
}
