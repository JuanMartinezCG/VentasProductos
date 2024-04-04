package ventaproducto.ventasproductos.Repository;



import ventaproducto.ventasproductos.AbstractIntegrationDBTest;
import ventaproducto.ventasproductos.entities.Cliente;
import ventaproducto.ventasproductos.repository.ClienteRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;


class ClienteRepositoryTest extends AbstractIntegrationDBTest {

    ClienteRepository clienteRepository;
    Cliente customer;

    @Autowired
    public ClienteRepositoryTest(ClienteRepository clienteRepository) {

        this.clienteRepository = clienteRepository;
    }

    //borrando clientes del repository
    @BeforeEach
    void setUp() {
        clienteRepository.deleteAll();
    }

    @Test
    void RetornarCliente(){
        
        customer = Cliente.builder()
        .id(1L)
        .nombre("juan")
        .email("tumama@gmail.com")
        .direccion("cra 26 C 48")
        .pedidos(null)
        .build();
        
        Cliente clienteGuardado=clienteRepository.save(customer);
        
        assertThat(clienteGuardado).isNotNull();
        assertThat(clienteGuardado.getNombre()).isEqualTo("juan");
    }

    @Test
    void BuscandoClienteId(){
        
        customer = Cliente.builder()
        .id(1L)
        .nombre("juan")
        .email("tumama@gmail.com")
        .direccion("cra 26 C 48")
        .pedidos(null)
        .build();

        Cliente clienteGuardado=clienteRepository.save(customer);
        
        Optional<Cliente> optionalCliente= clienteRepository.findById(clienteGuardado.getId());
        
        assertThat(optionalCliente).isPresent();
    }

    @Test
    void ActualizarCliente(){
        
        customer = Cliente.builder()
        .id(1L)
        .nombre("juan")
        .email("tumama@gmail.com")
        .direccion("cra 26 C 48")
        .pedidos(null)
        .build();

        Cliente clienteGuardado=clienteRepository.save(customer);
        
        clienteGuardado.setNombre("juan Camilo");
        clienteRepository.save(clienteGuardado);
        
        assertThat(clienteGuardado.getNombre()).isEqualTo("juan Camilo");
    }

    @Test
    void eliminarCliente(){
        
        customer = Cliente.builder()
        .id(1L)
        .nombre("juan")
        .email("tumama@gmail.com")
        .direccion("cra 26 C 48")
        .pedidos(null)
        .build();

        Cliente clienteSave=clienteRepository.save(customer);
        
        clienteRepository.deleteById(clienteSave.getId());
        
        assertThat(clienteRepository.findById(clienteSave.getId())).isEmpty();
    }

    @Test
    void BuscarClientePorEmail(){
        
        customer = Cliente.builder()
        .id(1L)
        .nombre("juan")
        .email("tumama@gmail.com")
        .direccion("cra 26 C 48")
        .pedidos(null)
        .build();

        clienteRepository.save(customer);
        
        Optional<Cliente> clienteEncontrado = clienteRepository.findByEmail("tumama@gmail.com");
        
        assertThat(clienteEncontrado).isPresent();
    }

    @Test
    void BuscarClientesPorDireccion(){
        
        customer = Cliente.builder()
        .nombre("juan")
        .direccion("Casa19")
        .email("tumama@gmail.com")
        .build();

        clienteRepository.save(customer);

        Cliente customer2 = new Cliente();

        customer2 = Cliente.builder()
        .nombre("Camilo")
        .direccion("Casa19")
        .email("elbicho@gmail.com")
        .build();

        clienteRepository.save(customer2);
        
        Optional<List<Cliente>> ListaClientes=clienteRepository.findByDireccion("Casa19");
        
        assertThat(ListaClientes).isPresent();
        assertThat(ListaClientes.get()).hasSize(2);

    }

    @Test
    void BuscarClientePorNombre(){

        customer = Cliente.builder()
        .nombre("juan")
        .direccion("Casa19")
        .email("tumama@gmail.com")
        .build();

        clienteRepository.save(customer);

        Cliente customer2 = new Cliente();

        customer2 = Cliente.builder()
        .nombre("juan")
        .direccion("Casa19")
        .email("elbicho@gmail.com")
        .build();

        clienteRepository.save(customer2);

        Optional<List<Cliente>> ListaClientes=clienteRepository.findByNombreStartingWith("juan");
        
        assertThat(ListaClientes).isPresent();
        assertThat(ListaClientes.get()).hasSize(2);
    }
}
