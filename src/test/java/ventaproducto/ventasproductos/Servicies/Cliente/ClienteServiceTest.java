package ventaproducto.ventasproductos.Servicies.Cliente;

import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;

import ventaproducto.ventasproductos.dto.Cliente.ClienteDto;
import ventaproducto.ventasproductos.dto.Cliente.ClienteDtoSave;
import ventaproducto.ventasproductos.dto.Cliente.ClienteMapper;
import ventaproducto.ventasproductos.entities.Cliente;
import static org.mockito.BDDMockito.given;
import ventaproducto.ventasproductos.repository.ClienteRepository;
import ventaproducto.ventasproductos.servicies.Cliente.ClienteService;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {
    
    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    Cliente customer;

    ClienteMapper clienteMapper;

    @BeforeEach
    void setUp() {
        /*cliente = Cliente.builder()
        .id(1L)
        .nombre("juan")
        .email("tumama@gmail.com")
        .direccion("la de tu mama")
        .pedidos(null)
        .build();*/

        clienteMapper = new ClienteMapper();
        clienteService=new ClienteService(clienteRepository);
        customer = new Cliente();
        customer.setId(1L);
        customer.setNombre("juan");
        customer.setEmail("tumama@gmail.com");
        customer.setDireccion("la de tu mama");
        customer.setPedidos(null);
        
        clienteRepository.save(customer);
    }

    @Test
    void testGuardarCliente() {
        /*cliente = Cliente.builder()
        .id(1L)
        .nombre("juan")
        .email("tumama@gmail.com")
        .direccion("la de tu mama")
        .pedidos(null)
        .build();*/

        customer.setId(1L);
        customer.setNombre("juan");
        customer.setEmail("tumama@gmail.com");
        customer.setDireccion("la de tu mama");
        customer.setPedidos(null);

        given(clienteRepository.save(any())).willReturn(customer);
        
        ClienteDtoSave clienteGuardado = new ClienteDtoSave(
            "juan",
            "tumama@gmail.com",
            "la de tu mama");
        
        ClienteDto clienteDto = clienteService.guardarCliente(clienteGuardado);

        assertThat(clienteDto).isNotNull();
        assertThat(clienteDto.id()).isEqualTo(1l);
    }

}
