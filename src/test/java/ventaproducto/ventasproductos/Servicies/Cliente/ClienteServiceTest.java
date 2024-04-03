package ventaproducto.ventasproductos.Servicies.Cliente;

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
import ventaproducto.ventasproductos.dto.Cliente.ClienteMapperImpl;
import ventaproducto.ventasproductos.repository.ClienteRepository;
import ventaproducto.ventasproductos.servicies.Cliente.ClienteService;
import ventaproducto.ventasproductos.entities.Cliente;
import static org.mockito.BDDMockito.given;



@ExtendWith(MockitoExtension.class)
 public class ClienteServiceTest {
    
    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;
    Cliente customer; 
    ClienteMapper clienteMapper;

    @BeforeEach
    void setUp() {
        
        clienteMapper = new ClienteMapperImpl();
        clienteService = new ClienteService(clienteRepository);
        customer = new Cliente();
        /*customer = Cliente.builder()
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
        
        clienteRepository.save(customer);
    }

    @Test
    public void testGuardarCliente() {
        /*customer = Cliente.builder()
        .id(1L)
        .nombre("juan")
        .email("tumama@gmail.com")
        .direccion("la de tu mama")
        .pedidos(null)
        .build();*/

        customer.setId(1l);
        customer.setNombre("juan");
        customer.setEmail("tumama@gmail.com");
        customer.setDireccion("la de tu mama");
        customer.setPedidos(null);

        given(clienteRepository.save(any())).willReturn(customer);
        
        ClienteDtoSave clienteGuardado = new ClienteDtoSave(
            1l,
            "juan",
            "tumama@gmail.com",
            "la de tu mama");
        
        ClienteDto clienteDto = clienteService.guardarCliente(clienteGuardado);

        assertThat(clienteDto).isNotNull();
        assertThat(clienteDto.id()).isEqualTo(1l);
    }

}
