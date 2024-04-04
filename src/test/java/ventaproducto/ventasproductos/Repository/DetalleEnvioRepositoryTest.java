package ventaproducto.ventasproductos.Repository;

import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ventaproducto.ventasproductos.AbstractIntegrationDBTest;
import ventaproducto.ventasproductos.Enums.StatusPedido;
import ventaproducto.ventasproductos.entities.Cliente;
import ventaproducto.ventasproductos.entities.DetalleEnvio;
import ventaproducto.ventasproductos.entities.Pedido;
import ventaproducto.ventasproductos.repository.ClienteRepository;
import ventaproducto.ventasproductos.repository.DetalleEnvioRepository;
import ventaproducto.ventasproductos.repository.PedidoRepository;

public class DetalleEnvioRepositoryTest extends AbstractIntegrationDBTest {
    
    DetalleEnvioRepository detalleEnvioRepo;
    ClienteRepository clienteRepo;
    PedidoRepository pedidoRepo;
    Cliente customer;
    
    @Autowired
    public DetalleEnvioRepositoryTest(DetalleEnvioRepository detalleEnvioRepo, ClienteRepository clienteRepo,
            PedidoRepository pedidoRepo) {
        this.detalleEnvioRepo = detalleEnvioRepo;
        this.clienteRepo = clienteRepo;
        this.pedidoRepo = pedidoRepo;
    }

    @BeforeEach
    void setUp() {
        detalleEnvioRepo.deleteAll();
        clienteRepo.deleteAll();
        pedidoRepo.deleteAll();
    }
    
    
    @Test
    void CreandoDetallaEnvio_y_Guardandolo(){
        
        customer = Cliente.builder()
        .nombre("juan")
        .email("tumama@gmail.com")
        .direccion("cra 26 C 48")
        .build();

        Cliente clienteGuardado = clienteRepo.save(customer);

        Pedido pedido;
        pedido = Pedido.builder()
        .fechaPedido(LocalDateTime.of(2024, Month.AUGUST,10,8,20))
        .estatusPedido(StatusPedido.ENVIADO)
        .cliente(clienteGuardado)
        .build();
        
        Pedido pedidoGuardado = pedidoRepo.save(pedido);

        DetalleEnvio detalleEnvio=DetalleEnvio.builder()
                .direccion(customer.getDireccion())
                .transportadora("Rappi")
                .numeroGuia("10V293A84T83")
                .pedido(pedidoGuardado)
                .build();
        
        DetalleEnvio detalleEnviGuardado=detalleEnvioRepo.save(detalleEnvio);
        
        assertThat(detalleEnviGuardado).isNotNull();
        assertThat(detalleEnvioRepo.findById(detalleEnviGuardado.getId())).isPresent();
    }

    
    @Test
    void Busca_Un_DetalleEnvio_Por_ID_y_dice_si_encuentra(){

        customer = Cliente.builder()
        .nombre("juan")
        .email("tumama@gmail.com")
        .direccion("cra 26 C 48")
        .build();
        
        Cliente clienteGuardado=clienteRepo.save(customer);

        Pedido pedido;
        pedido = Pedido.builder()
        .fechaPedido(LocalDateTime.of(2024, Month.AUGUST,10,8,20))
        .estatusPedido(StatusPedido.ENVIADO)
        .cliente(clienteGuardado)
        .build();
        
        Pedido pedidoGuardado = pedidoRepo.save(pedido);

        DetalleEnvio detalleEnvio=DetalleEnvio.builder()
                .direccion(customer.getDireccion())
                .transportadora("Rappi")
                .numeroGuia("10V293A84T83")
                .pedido(pedidoGuardado)
                .build();
        
        DetalleEnvio detalleEnviGuardado=detalleEnvioRepo.save(detalleEnvio);
        
        Optional<DetalleEnvio> optionalDetalleEnvio=detalleEnvioRepo.findById(detalleEnviGuardado.getId());
        
        assertThat(optionalDetalleEnvio).isPresent();
    }

    @Test
    void ActualizarDetallePedido(){
        customer = Cliente.builder()
        .nombre("juan")
        .email("tumama@gmail.com")
        .direccion("cra 26 C 48")
        .build();
        
        Cliente clienteGuardado=clienteRepo.save(customer);

        Pedido pedido;
        pedido = Pedido.builder()
        .fechaPedido(LocalDateTime.of(2024, Month.AUGUST,10,8,20))
        .estatusPedido(StatusPedido.ENVIADO)
        .cliente(clienteGuardado)
        .build();
        
        Pedido pedidoGuardado = pedidoRepo.save(pedido);

        DetalleEnvio detalleEnvio=DetalleEnvio.builder()
                .direccion(customer.getDireccion())
                .transportadora("Rappi")
                .numeroGuia("10V293A84T83")
                .pedido(pedidoGuardado)
                .build();
        
        DetalleEnvio detalleEnviGuardado=detalleEnvioRepo.save(detalleEnvio);
        
        detalleEnviGuardado.setNumeroGuia("FFF674DE2556");
        detalleEnvioRepo.save(detalleEnvio);
        
        assertThat(detalleEnviGuardado.getNumeroGuia()).isEqualTo("FFF674DE2556");
    }

    @Test
    void BorraDetalleEnvio(){
        
        customer = Cliente.builder()
        .nombre("juan")
        .email("tumama@gmail.com")
        .direccion("cra 26 C 48")
        .build();
        
        Cliente clienteGuardado=clienteRepo.save(customer);

        Pedido pedido;
        pedido = Pedido.builder()
        .fechaPedido(LocalDateTime.of(2024, Month.AUGUST,10,8,20))
        .estatusPedido(StatusPedido.ENVIADO)
        .cliente(clienteGuardado)
        .build();
        
        Pedido pedidoGuardado = pedidoRepo.save(pedido);

        DetalleEnvio detalleEnvio=DetalleEnvio.builder()
                .direccion(customer.getDireccion())
                .transportadora("Rappi")
                .numeroGuia("10V293A84T83")
                .pedido(pedidoGuardado)
                .build();
        
        DetalleEnvio detalleEnviGuardado=detalleEnvioRepo.save(detalleEnvio);

        detalleEnvioRepo.deleteById(detalleEnviGuardado.getId());
        
        assertThat(detalleEnvioRepo.findById(detalleEnviGuardado.getId())).isEmpty();

    }


    @Test
    void BuscarDetalleEnvio(){
        customer = Cliente.builder()
        .nombre("juan")
        .email("tumama@gmail.com")
        .direccion("cra 26 C 48")
        .build();
        
        Cliente clienteGuardado=clienteRepo.save(customer);

        Pedido pedido;
        pedido = Pedido.builder()
        .fechaPedido(LocalDateTime.of(2024, Month.AUGUST,10,8,20))
        .estatusPedido(StatusPedido.ENVIADO)
        .cliente(clienteGuardado)
        .build();
        
        Pedido pedidoGuardado = pedidoRepo.save(pedido);

        DetalleEnvio detalleEnvio=DetalleEnvio.builder()
                .direccion(customer.getDireccion())
                .transportadora("Rappi")
                .numeroGuia("10V293A84T83")
                .pedido(pedidoGuardado)
                .build();
        
        detalleEnvioRepo.save(detalleEnvio);
        
        Optional<DetalleEnvio>detalleEnvioGuardado= detalleEnvioRepo.findByPedidoId(pedido.getId());

        assertThat(detalleEnvioGuardado).isPresent();
        assertThat(detalleEnvioGuardado.get().getTransportadora()).isEqualTo("Rappi");

    }

    @Test
    void ComprobandoListaDe_DetalleEnvios_por_Transportadora(){
        
        customer = Cliente.builder()
        .nombre("juan")
        .email("tumama@gmail.com")
        .direccion("cra 26 C 48")
        .build();
        Cliente clienteGuardado=clienteRepo.save(customer);


        Pedido pedido;
        pedido = Pedido.builder()
        .fechaPedido(LocalDateTime.of(2024, Month.AUGUST,10,8,20))
        .estatusPedido(StatusPedido.ENVIADO)
        .cliente(clienteGuardado)
        .build();
        pedidoRepo.save(pedido);

        Pedido pedido2;
        pedido2 = Pedido.builder()
        .fechaPedido(LocalDateTime.of(2024, Month.AUGUST,10,8,20))
        .estatusPedido(StatusPedido.ENVIADO)
        .cliente(clienteGuardado)
        .build();
        pedidoRepo.save(pedido2);


        DetalleEnvio detalleEnvio=DetalleEnvio.builder()
                .direccion(customer.getDireccion())
                .transportadora("Rappi")
                .numeroGuia("10V293OIHFD")
                .pedido(pedido)
                .build();
        
        detalleEnvioRepo.save(detalleEnvio);

        DetalleEnvio detalleEnvio2=DetalleEnvio.builder()
                .direccion(customer.getDireccion())
                .transportadora("Rappi")
                .numeroGuia("10V293A84T83")
                .pedido(pedido2)
                .build();
        
        detalleEnvioRepo.save(detalleEnvio2);

        
        Optional<List<DetalleEnvio>>detalleEnvios=detalleEnvioRepo.findByTransportadora("Rappi");
        
        assertThat(detalleEnvios).isNotEmpty();
        assertThat(detalleEnvios.get()).hasSize(2);
    }
}
