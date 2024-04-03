package ventaproducto.ventasproductos.servicies.Cliente;

import ventaproducto.ventasproductos.dto.Cliente.ClienteDto;
import ventaproducto.ventasproductos.dto.Cliente.ClienteDtoSave;
import ventaproducto.ventasproductos.dto.Cliente.ClienteMapper;
import ventaproducto.ventasproductos.entities.Cliente;
import ventaproducto.ventasproductos.repository.ClienteRepository;

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
        Cliente client = ClienteMapper.INSTANCE.Formato(clienteJSON);
        Cliente clienteGuardado = clienteRepository.save(client);
        return ClienteMapper.INSTANCE.clienteToClienteDto(clienteGuardado);
    }
/*
    @Override
    public List<ClienteDto> encontrarTodosClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream()
                .map(clienteMapper::clienteToClienteDto)
                .collect(Collectors.toList());
    }

    @Override
    public ClienteDto obtenerClientePorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));
        return clienteMapper.clienteToClienteDto(cliente);
    }
     
    @Override
    public ClienteDto actualizarCliente(Long id, ClienteDtoSave clienteDto) {
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));

        Cliente clienteActualizado = clienteMapper.clienteDtoToCliente(clienteDto);
        clienteActualizado.setId(id);

        Cliente clienteGuardado = clienteRepository.save(clienteActualizado);
        return clienteMapper.clienteToClienteDto(clienteGuardado);
    }

    @Override
    public void eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    //---------------------------------------------------------------------------

    @Override
    public List<ClienteDto> encontrarClientesPorEmail(String email) {
        List<Cliente> clientes = clienteRepository.findByEmail(email);
        return clientes.stream()
                .map(clienteMapper::clienteToClienteDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClienteDto> encontrarClientesPorDireccion(String direccion) {
        List<Cliente> clientes = clienteRepository.findByDireccion(direccion);
        return clientes.stream()
                .map(clienteMapper::clienteToClienteDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClienteDto> encontrarClientesPorNombre(String nombre) {
        List<Cliente> clientes = clienteRepository.findByNombre(nombre);
        return clientes.stream()
                .map(clienteMapper::clienteToClienteDto)
                .collect(Collectors.toList());
    }*/
}
