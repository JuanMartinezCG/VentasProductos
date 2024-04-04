package ventaproducto.ventasproductos.Servicies.Cliente;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import ventaproducto.ventasproductos.dto.Cliente.ClienteDto;
import ventaproducto.ventasproductos.dto.Cliente.ClienteDtoSave;
import ventaproducto.ventasproductos.dto.Cliente.ClienteMapper;
import ventaproducto.ventasproductos.dto.Cliente.ClienteMapperImpl;
import ventaproducto.ventasproductos.entities.Cliente;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.BDDMockito.willDoNothing;

import ventaproducto.ventasproductos.repository.ClienteRepository;
import ventaproducto.ventasproductos.servicies.Cliente.ClienteService;



@RunWith(MockitoJUnitRunner.class)
public class ClienteServiceTest{
    
    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;
    
    Cliente customer = new Cliente();
    ClienteMapper clienteMapper;

    @BeforeEach
    void setUp() {
        
        clienteMapper = new ClienteMapperImpl();
        clienteService = new ClienteService(clienteRepository);
        customer = Cliente.builder()
        .id(1L)
        .nombre("juan")
        .email("tumama@gmail.com")
        .direccion("cra 26 C 48")
        .pedidos(null)
        .build();
        
        clienteRepository.save(customer);
    }

    @Test
    public void testGuardarCliente() {
        customer = Cliente.builder()
        .id(1L)
        .nombre("juan")
        .email("tumama@gmail.com")
        .direccion("cra 26 C 48")
        .pedidos(null)
        .build();

        given(clienteRepository.save(any())).willReturn(customer);
        
        ClienteDtoSave clienteGuardado = new ClienteDtoSave(
            1l,
            "juan",
            "tumama@gmail.com",
            "cra 26 C 48");
        
        ClienteDto clienteDto = clienteService.guardarCliente(clienteGuardado);

        assertThat(clienteDto).isNotNull();
        assertThat(clienteDto.id()).isEqualTo(1l);
    }
    
    @Test
    public void testactualizarCliente() {
        customer = Cliente.builder()
        .id(1L)
        .nombre("juan")
        .email("tumama@gmail.com")
        .direccion("cra 26 C 48")
        .pedidos(null)
        .build();

        given(clienteRepository.save(any())).willReturn(customer);
        
        ClienteDtoSave clienteGuardado = new ClienteDtoSave(
            1L,
            "juan",
            "tumama@gmail.com",
            "cra 26 C 48");
        
        ClienteDto clienteDto = clienteService.guardarCliente(clienteGuardado);
        customer.setNombre("camilo");
        clienteDto = clienteService.guardarCliente(clienteGuardado);

        assertThat(clienteDto).isNotNull();
        assertThat(clienteDto.id()).isEqualTo(1l);
    }

    @Test
    public void testgetAllClientes(){
        Optional<List<ClienteDto>> clienteDtoList = clienteService.getAllClientes();
        assertThat(clienteDtoList).isNotNull();
    }

    @Test
    public void eliminarCliente(){
        given(clienteRepository.findById(1L)).willReturn(Optional.of(customer));
        willDoNothing().given(clienteRepository).delete(customer);
        clienteService.eliminarCliente(1L);
        verify(clienteRepository, times(1)).delete(customer);
    }

    @Test
    public void BuscarDirrecionCliente (){
        String DireccionCliente="cra 26 C 48";
        given(clienteRepository.findByDireccion(DireccionCliente)).willReturn(Optional.of(List.of(customer)));
        Optional<List<ClienteDto>> clienteDtos = clienteService.findByDireccion(DireccionCliente);
        assertThat(clienteDtos).isNotNull();
    }

    @Test
    public void BuscarporNombre (){
        String NombreCliente="juan";
        given(clienteRepository.findByNombreStartingWith(NombreCliente)).willReturn(Optional.of(List.of(customer)));
        Optional<List<ClienteDto>> clienteDtos = clienteService.findByNombreStartingWith(NombreCliente);
        assertThat(clienteDtos).isNotNull();
    }
}

