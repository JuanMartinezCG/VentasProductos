package ventaproducto.ventasproductos.Repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ventaproducto.ventasproductos.AbstractIntegrationDBTest;
import ventaproducto.ventasproductos.entities.Cliente;
import ventaproducto.ventasproductos.repository.ClienteRepository;


class ClienteRepositoryTest extends AbstractIntegrationDBTest{

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
        .direccion("la de tu mama")
        .pedidos(null)
        .build();

        Cliente clienteGuardado = clienteRepository.save(customer);

        assertThat(clienteGuardado).isNotNull();
        assertThat(clienteGuardado.getNombre()).isEqualTo("juan");
    }

}
