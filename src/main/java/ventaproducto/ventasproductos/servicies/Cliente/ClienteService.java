package ventaproducto.ventasproductos.servicies.Cliente;

import ventaproducto.ventasproductos.dto.Cliente.ClienteDto;
import ventaproducto.ventasproductos.dto.Cliente.ClienteDtoSave;
import ventaproducto.ventasproductos.dto.Cliente.ClienteMapper;
import ventaproducto.ventasproductos.entities.Cliente;
import ventaproducto.ventasproductos.repository.ClienteRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ClienteService implements ClienteServiceInterface {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    // -----------------------------------------------------------------------------

    @Override
    public ClienteDto guardarCliente(ClienteDtoSave clienteJSON) {
        Cliente cliente = ClienteMapper.INSTANCE.Formato(clienteJSON);
        Cliente clienteGuardado = clienteRepository.save(cliente);
        return ClienteMapper.INSTANCE.clienteToClienteDto(clienteGuardado);
    }
    
    @Override
    public ClienteDto actualizarCliente(ClienteDtoSave clienteJSON) {

        Cliente clienteActual = ClienteMapper.INSTANCE.Formato(clienteJSON);

        Cliente clienteExistente = clienteRepository.findById(clienteJSON.id())
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + clienteJSON.id()));

        clienteExistente.setNombre(clienteActual.getNombre());
        clienteExistente.setDireccion(clienteActual.getDireccion());
        clienteExistente.setEmail(clienteActual.getEmail());
        clienteExistente.setPedidos(clienteActual.getPedidos());
    
        clienteExistente = clienteRepository.save(clienteExistente);
        return ClienteMapper.INSTANCE.clienteToClienteDto(clienteExistente);
    }

    public Optional<List<ClienteDto>> getAllClientes(){
        List<Cliente> clientes = clienteRepository.findAll();
        List<ClienteDto> clientesDtos = clientes.stream().map(ClienteMapper.INSTANCE:: clienteToClienteDto).collect(Collectors.toList());
        return Optional.of(clientesDtos);
    }

    public ClienteDto findById(Long id){
        Cliente clienteExistente = clienteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("||| Cliente no encontrado con ID: " + id));
        return ClienteMapper.INSTANCE.clienteToClienteDto(clienteExistente);    
    }

    public void eliminarCliente(Long id){
        Cliente clienteExistente = clienteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException(" ||| Cliente no encontrado con ID: " + id));

        clienteRepository.delete(clienteExistente);
    }

    public Optional<ClienteDto> findByEmail(String email){
        Cliente clienteExistente = clienteRepository.findByEmail(email)
        .orElseThrow(()-> new RuntimeException("||| Cliente no encontrado con email: " + email));
        return Optional.ofNullable(ClienteMapper.INSTANCE.clienteToClienteDto(clienteExistente));
    }

    @Override
    public Optional<List<ClienteDto>> findByDireccion(String direccion){

        List<Cliente> clienteExistente = clienteRepository.findByDireccion(direccion)
        .orElseThrow(()->new RuntimeException("||| cliente no Existe"+ direccion));
        List<ClienteDto> clientesDto = clienteExistente.stream().map(ClienteMapper.INSTANCE::clienteToClienteDto).collect(Collectors.toList());
        return Optional.of(clientesDto);
    }

    @Override
    public Optional<List<ClienteDto>> findByNombreStartingWith(String nombre){

        List<Cliente> clienteExistente = clienteRepository.findByDireccion(nombre)
        .orElseThrow(()->new RuntimeException("||| Cliente no Existe"+ nombre));
        List<ClienteDto> clientesDto = clienteExistente.stream().map(ClienteMapper.INSTANCE::clienteToClienteDto).collect(Collectors.toList());
        return Optional.of(clientesDto);
    }

}
