package ventaproducto.ventasproductos.Repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import ventaproducto.ventasproductos.AbstractIntegrationDBTest;
import ventaproducto.ventasproductos.Enums.StatusPedido;
import ventaproducto.ventasproductos.entities.Cliente;
import ventaproducto.ventasproductos.entities.ItemPedido;
import ventaproducto.ventasproductos.entities.Pedido;
import ventaproducto.ventasproductos.entities.Producto;
import ventaproducto.ventasproductos.repository.ClienteRepository;
import ventaproducto.ventasproductos.repository.ItemPedidoRepository;
import ventaproducto.ventasproductos.repository.PedidoRepository;
import ventaproducto.ventasproductos.repository.ProductoRepository;

public class PedidoRepositoryTest extends AbstractIntegrationDBTest{
    
    PedidoRepository pedidoRepository;
    ClienteRepository clienteRepository;
    ProductoRepository productoRepository;
    ItemPedidoRepository itemPedidoRepository;
    Cliente customer;
    Pedido pedido;
    
    @Autowired
    public PedidoRepositoryTest(PedidoRepository pedidoRepository, ClienteRepository clienteRepository,ItemPedidoRepository itemPedidoRepository,ProductoRepository productoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository=clienteRepository;
        this.itemPedidoRepository=itemPedidoRepository;
        this.productoRepository=productoRepository;
    }

    @BeforeEach
    void setUp() {
        pedidoRepository.deleteAll();
        clienteRepository.deleteAll();
        itemPedidoRepository.deleteAll();
        productoRepository.deleteAll();
    }

    @Test
    void CreandoPedido(){

        customer = Cliente.builder()
            .nombre("juan")
            .email("tumama@gmail.com")
            .direccion("cra 26 C 48")
            .build();
        clienteRepository.save(customer);

        pedido = Pedido.builder()
            .fechaPedido(LocalDateTime.of(2024, Month.AUGUST,10,8,20))
            .estatusPedido(StatusPedido.ENVIADO)
            .cliente(customer)
            .build();
        pedidoRepository.save(pedido);

        Pedido pedidoGuardado = pedidoRepository.save(pedido);

        assertThat(pedidoGuardado).isNotNull();
    }

    @Test
    void EncontrarPedido(){
        
        customer = Cliente.builder()
            .nombre("juan")
            .email("tumama@gmail.com")
            .direccion("cra 26 C 48")
            .build();
        clienteRepository.save(customer);

        pedido = Pedido.builder()
            .fechaPedido(LocalDateTime.of(2024, Month.AUGUST,10,8,20))
            .estatusPedido(StatusPedido.ENVIADO)
            .cliente(customer)
            .build();
        Pedido pedidoGuardado = pedidoRepository.save(pedido);

        Optional<Pedido> optionalPedido=pedidoRepository.findById(pedidoGuardado.getId());
        
        assertThat(optionalPedido).isPresent();
    }

    @Test
    void ActualizarPedido(){
        
        customer = Cliente.builder()
            .nombre("juan")
            .email("tumama@gmail.com")
            .direccion("cra 26 C 48")
            .build();
        clienteRepository.save(customer);

        pedido = Pedido.builder()
            .fechaPedido(LocalDateTime.of(2024, Month.AUGUST,10,8,20))
            .estatusPedido(StatusPedido.ENVIADO)
            .cliente(customer)
            .build();
        Pedido pedidoGuardado = pedidoRepository.save(pedido);
        
        pedidoGuardado.setEstatusPedido(StatusPedido.PENDIENTE);
        pedidoRepository.save(pedidoGuardado);

        assertThat(pedidoGuardado.getEstatusPedido()).isEqualTo(StatusPedido.PENDIENTE);
    }

    @Test
    void EliminarPedido(){
        
        customer = Cliente.builder()
            .nombre("juan")
            .email("tumama@gmail.com")
            .direccion("cra 26 C 48")
            .build();
        clienteRepository.save(customer);

        pedido = Pedido.builder()
            .fechaPedido(LocalDateTime.of(2024, Month.AUGUST,10,8,20))
            .estatusPedido(StatusPedido.ENVIADO)
            .cliente(customer)
            .build();
        Pedido pedidoGuardado = pedidoRepository.save(pedido);
        
        pedidoRepository.deleteById(pedidoGuardado.getId());
        
        assertThat(pedidoRepository.findById(pedidoGuardado.getId())).isEmpty();
    }


    @Test
    void ListPedidosEnesaFecha(){

        customer = Cliente.builder()
            .nombre("juan")
            .email("tumama@gmail.com")
            .direccion("cra 26 C 48")
            .build();
        clienteRepository.save(customer);

        pedido = Pedido.builder()
            .fechaPedido(LocalDateTime.of(2024, Month.AUGUST,10,8,20))
            .estatusPedido(StatusPedido.ENVIADO)
            .cliente(customer)
            .build();
        pedidoRepository.save(pedido);


        Pedido pedido2 = Pedido.builder()
            .fechaPedido(LocalDateTime.of(2024, Month.AUGUST,10,8,20))
            .estatusPedido(StatusPedido.ENVIADO)
            .cliente(customer)
            .build();
        pedidoRepository.save(pedido2);

        Pedido pedido3 = Pedido.builder()
            .fechaPedido(LocalDateTime.of(2024, Month.AUGUST,10,8,20))
            .estatusPedido(StatusPedido.ENVIADO)
            .cliente(customer)
            .build();
        pedidoRepository.save(pedido3);

        
        Optional<List<Pedido>> pedidosEncontrado= pedidoRepository.findByFechaPedidoBetween(LocalDateTime
                .of(2024,Month.JULY,1,10,20)
                ,LocalDateTime.of(2024,Month.AUGUST,9,20,20));
        
        assertThat(pedidosEncontrado).isPresent();
        assertThat(pedidosEncontrado.stream().toList()).hasSize(1);


    }
    
    @Test
    void Buscar_un_Pedido_y_clienteConUnStatus_Y_RetornaUnaList(){
    
        customer = Cliente.builder()
            .nombre("juan")
            .email("tumama@gmail.com")
            .direccion("cra 26 C 48")
            .build();
        clienteRepository.save(customer);

        Cliente customer2 = Cliente.builder()
            .nombre("Kevin")
            .email("elbicho@gmail.com")
            .direccion("cra 26 C 48")
            .build();
        Cliente customerSave = clienteRepository.save(customer2);


        Pedido pedido= new Pedido();
        pedido.setFechaPedido(LocalDateTime.of(2024,Month.AUGUST,10,8,20));
        pedido.setEstatusPedido(StatusPedido.ENVIADO);
        pedido.setCliente(customer);
        pedidoRepository.save(pedido);

        Pedido pedido2= new Pedido();
        pedido2.setFechaPedido(LocalDateTime.of(2024,Month.JULY,10,8,20));
        pedido2.setEstatusPedido(StatusPedido.ENTREGADO);
        pedido2.setCliente(customerSave);
        pedidoRepository.save(pedido2);

        
        Optional<List<Pedido>> pedidosEncontrado=pedidoRepository.findByClienteAndstatusPedido(customer2.getId(),StatusPedido.ENTREGADO);
        
        assertThat(pedidosEncontrado).isPresent();
        assertThat(pedidosEncontrado.stream().toList()).hasSize(1);
        Pedido pedidoExite=pedidosEncontrado.get().get(0);
        assertThat(pedidoExite.getEstatusPedido()).isEqualTo(StatusPedido.ENTREGADO);
    }
    
    @Test
    void GivePedidoAndCliente_WhenFindClienteAndItemsPedidos_ReturnList(){
        
        customer = Cliente.builder()
            .nombre("juan")
            .email("tumama@gmail.com")
            .direccion("cra 26 C 48")
            .build();
        clienteRepository.save(customer);

        Producto producto = Producto.builder()
        .nombre("Cerveza Aguila")
        .stock(2)
        .price(2000).build();
        productoRepository.save(producto);

        Pedido pedido= new Pedido();
        pedido.setFechaPedido(LocalDateTime.of(2024,Month.AUGUST,10,8,20));
        pedido.setEstatusPedido(StatusPedido.ENVIADO);
        pedido.setCliente(customer);
        pedidoRepository.save(pedido);


        ItemPedido itemPedido = ItemPedido.builder()
        .pedido(pedido)
        .cantidad(14)
        .producto(producto)
        .precioUnitario(12)
        .build();
        

        itemPedidoRepository.save(itemPedido);

        Optional<List<Pedido>> pedidosFound= pedidoRepository.findByClienteWhithItemPedidos(customer);

        assertThat(pedidosFound).isPresent();
        assertThat(pedidosFound.stream().toList()).hasSize(1);

        Pedido pedidos = pedidosFound.get().get(0);
        assertThat(pedidos).isNotNull();


    }
}
