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
import ventaproducto.ventasproductos.entities.ItemPedido;
import ventaproducto.ventasproductos.entities.Pedido;
import ventaproducto.ventasproductos.entities.Producto;
import ventaproducto.ventasproductos.repository.ClienteRepository;
import ventaproducto.ventasproductos.repository.ItemPedidoRepository;
import ventaproducto.ventasproductos.repository.PedidoRepository;
import ventaproducto.ventasproductos.repository.ProductoRepository;

public class ItemPedidoRepositoryTest extends AbstractIntegrationDBTest {
    
    ItemPedidoRepository itemPedidoRepository;
    ClienteRepository clienteRepository;
    PedidoRepository pedidoRepository;
    ProductoRepository productoRepository;
    Cliente customer;
    Pedido pedido;
    Producto producto;
    ItemPedido itemPedido;
    
    @Autowired
    public ItemPedidoRepositoryTest(ItemPedidoRepository itemPedidoRepository, ClienteRepository pedidoRepository,
            PedidoRepository clienteRepository, ProductoRepository productoRepository) {
        this.itemPedidoRepository = itemPedidoRepository;
        this.clienteRepository = pedidoRepository;
        this.pedidoRepository = clienteRepository;
        this.productoRepository = productoRepository;
    }   

    @BeforeEach
    void setUp() {
        itemPedidoRepository.deleteAll();
        pedidoRepository.deleteAll();
        clienteRepository.deleteAll();
        productoRepository.deleteAll();
    }

    @Test
    void GuardadoItemPedido(){
        
        customer = Cliente.builder()
            .nombre("juan")
            .email("tumama@gmail.com")
            .direccion("cra 26 C 48")
            .build();
        Cliente clienteGuardado = clienteRepository.save(customer);

        pedido = Pedido.builder()
            .fechaPedido(LocalDateTime.of(2024, Month.AUGUST,10,8,20))
            .estatusPedido(StatusPedido.ENVIADO)
            .cliente(clienteGuardado)
            .build();
        Pedido pedidoGuardado = pedidoRepository.save(pedido);
                
        Producto producto= Producto.builder()
            .nombre("cerveza Aguila")
            .stock(12)
            .price(15000)
            .build();
        Producto productoGuardado=productoRepository.save(producto);

        ItemPedido itemPedido=ItemPedido.builder()
            .cantidad(200)
            .precioUnitario(60000)
            .pedido(pedidoGuardado)
            .producto(productoGuardado)
            .build();
        
        ItemPedido itemPedidoGuardado=itemPedidoRepository.save(itemPedido);
        
        assertThat(itemPedidoGuardado).isNotNull();
        assertThat(itemPedidoRepository.findById(itemPedidoGuardado.getId())).isPresent();
    }

    
    @Test
    void Busca_Un_ItemPedido_Por_ID_y_dice_si_encuentra(){
        
        customer = Cliente.builder()
            .nombre("juan")
            .email("tumama@gmail.com")
            .direccion("cra 26 C 48")
            .build();
        Cliente clienteGuardado = clienteRepository.save(customer);

        pedido = Pedido.builder()
            .fechaPedido(LocalDateTime.of(2024, Month.AUGUST,10,8,20))
            .estatusPedido(StatusPedido.ENVIADO)
            .cliente(clienteGuardado)
            .build();
        Pedido pedidoGuardado = pedidoRepository.save(pedido);

        
        Producto producto= Producto.builder()
            .nombre("cerveza Aguila")
            .stock(12)
            .price(15000)
            .build();
        Producto productoGuardado=productoRepository.save(producto);

        ItemPedido itemPedido=ItemPedido.builder()
                .cantidad(200)
                .precioUnitario(60000)
                .pedido(pedidoGuardado)
                .producto(productoGuardado)
                .build();
        
        ItemPedido itemPedidoGuardado=itemPedidoRepository.save(itemPedido);
        
        Optional<ItemPedido> optionalItemPedido=itemPedidoRepository.findById(itemPedidoGuardado.getId());
        
        assertThat(optionalItemPedido).isPresent();
    }

    @Test
    void ActualizarItemPedido(){
        
        customer = Cliente.builder()
            .nombre("juan")
            .email("tumama@gmail.com")
            .direccion("cra 26 C 48")
            .build();
        Cliente clienteGuardado = clienteRepository.save(customer);

        pedido = Pedido.builder()
            .fechaPedido(LocalDateTime.of(2024, Month.AUGUST,10,8,20))
            .estatusPedido(StatusPedido.ENVIADO)
            .cliente(clienteGuardado)
            .build();
        Pedido pedidoGuardado = pedidoRepository.save(pedido);

        
        Producto producto= Producto.builder()
            .nombre("cerveza Aguila")
            .stock(12)
            .price(15000)
            .build();
        Producto productoGuardado=productoRepository.save(producto);

        ItemPedido itemPedido = ItemPedido.builder()
                .cantidad(200)
                .precioUnitario(400000)
                .pedido(pedidoGuardado)
                .producto(productoGuardado)
                .build();
        ItemPedido itemPedidoGuardado=itemPedidoRepository.save(itemPedido);
        
        itemPedidoGuardado.setCantidad(190);
        itemPedidoRepository.save(itemPedidoGuardado);
        
        assertThat(itemPedidoGuardado.getCantidad()).isEqualTo(190);
    }

    @Test
    void EliminarItemPedido(){
        
        customer = Cliente.builder()
            .nombre("juan")
            .email("tumama@gmail.com")
            .direccion("cra 26 C 48")
            .build();
        Cliente clienteGuardado = clienteRepository.save(customer);

        pedido = Pedido.builder()
            .fechaPedido(LocalDateTime.of(2024, Month.AUGUST,10,8,20))
            .estatusPedido(StatusPedido.ENVIADO)
            .cliente(clienteGuardado)
            .build();
        Pedido pedidoGuardado = pedidoRepository.save(pedido);

        
        Producto producto= Producto.builder()
            .nombre("cerveza Aguila")
            .stock(12)
            .price(15000)
            .build();
        Producto productoGuardado=productoRepository.save(producto);

        ItemPedido itemPedido = ItemPedido.builder()
                .cantidad(200)
                .precioUnitario(400000)
                .pedido(pedidoGuardado)
                .producto(productoGuardado)
                .build();
        ItemPedido itemPedidoGuardado=itemPedidoRepository.save(itemPedido);
        
        itemPedidoRepository.deleteById(itemPedidoGuardado.getId());
        
        assertThat(itemPedidoRepository.findById(itemPedidoGuardado.getId())).isEmpty();
    }

    @Test
    void GiveAnItemPedido_WhenSearchForIdPedido_ReturnAList(){
        
        Cliente cliente=new Cliente();
        cliente.setNombre("juan");
        cliente.setDireccion("cra 24 casa 45");
        cliente.setEmail("tumama@gmail.com");
        Cliente clienteGuardado=clienteRepository.save(cliente);

        Pedido pedido=new Pedido();
        pedido.setFechaPedido(LocalDateTime.of(2024, Month.AUGUST,10,8,20));
        pedido.setEstatusPedido(StatusPedido.ENVIADO);
        pedido.setCliente(clienteGuardado);
        Pedido pedidoGuardado = pedidoRepository.save(pedido);

        Producto producto=new Producto();
        producto.setNombre("cerveza aguila");
        producto.setStock(12);
        producto.setPrice(15);
        Producto productoGuardado = productoRepository.save(producto);

        ItemPedido itemPedido = ItemPedido.builder()
                .cantidad(200)
                .precioUnitario(400000)
                .pedido(pedidoGuardado)
                .producto(productoGuardado)
                .build();
        itemPedidoRepository.save(itemPedido);
        
        ItemPedido itemPedido2 = ItemPedido.builder()
                .cantidad(200)
                .precioUnitario(400000)
                .pedido(pedidoGuardado)
                .producto(productoGuardado)
                .build();
        itemPedidoRepository.save(itemPedido2);
        
        ItemPedido itemPedido3 = ItemPedido.builder()
                .cantidad(200)
                .precioUnitario(400000)
                .pedido(pedidoGuardado)
                .producto(productoGuardado)
                .build();
        itemPedidoRepository.save(itemPedido3);

        
        Optional<List<ItemPedido>> itemPedidosExiste= itemPedidoRepository.findByPedidoId(pedido.getId());
        
        assertThat(itemPedidosExiste).isNotEmpty();
        assertThat(itemPedidosExiste.get()).hasSize(3);
    }
    @Test
    void Lista_de_Producto_buscada_por_ID(){
        
        Cliente cliente=new Cliente();
        cliente.setNombre("juan");
        cliente.setDireccion("cra 24 casa 45");
        cliente.setEmail("tumama@gmail.com");
        Cliente clienteGuardado=clienteRepository.save(cliente);

        Pedido pedido=new Pedido();
        pedido.setFechaPedido(LocalDateTime.of(2024, Month.AUGUST,10,8,20));
        pedido.setEstatusPedido(StatusPedido.ENVIADO);
        pedido.setCliente(clienteGuardado);
        Pedido pedidoGuardado = pedidoRepository.save(pedido);

        Producto producto=new Producto();
        producto.setNombre("cerveza aguila");
        producto.setStock(12);
        producto.setPrice(15);
        Producto productoGuardado=productoRepository.save(producto);

        ItemPedido itemPedido = ItemPedido.builder()
                .cantidad(200)
                .precioUnitario(400000)
                .pedido(pedidoGuardado)
                .producto(productoGuardado)
                .build();
        itemPedidoRepository.save(itemPedido);
        
        ItemPedido itemPedido2 = ItemPedido.builder()
                .cantidad(200)
                .precioUnitario(400000)
                .pedido(pedidoGuardado)
                .producto(productoGuardado)
                .build();
        itemPedidoRepository.save(itemPedido2);
        
        ItemPedido itemPedido3 = ItemPedido.builder()
                .cantidad(200)
                .precioUnitario(400000)
                .pedido(pedidoGuardado)
                .producto(productoGuardado)
                .build();
        itemPedidoRepository.save(itemPedido3);
        
        Optional<List<ItemPedido>> itemPedidosFound= itemPedidoRepository.findByProductoId(producto.getId());
        
        assertThat(itemPedidosFound).isNotEmpty();
        assertThat(itemPedidosFound.get()).hasSize(3);

    }

    @Test
    void CalculaTotalDeVentasPorUnProducto(){
        
        Cliente cliente=new Cliente();
        cliente.setNombre("juan");
        cliente.setDireccion("cra 24 casa 45");
        cliente.setEmail("tumama@gmail.com");
        Cliente clienteGuardado=clienteRepository.save(cliente);

        Pedido pedido=new Pedido();
        pedido.setFechaPedido(LocalDateTime.of(2024, Month.AUGUST,10,8,20));
        pedido.setEstatusPedido(StatusPedido.ENVIADO);
        pedido.setCliente(clienteGuardado);
        Pedido pedidoGuardado = pedidoRepository.save(pedido);

        Producto producto=new Producto();
        producto.setNombre("cerveza aguila");
        producto.setStock(12);
        producto.setPrice(15);
        Producto productoGuardado=productoRepository.save(producto);

        ItemPedido itemPedido = ItemPedido.builder()
                .cantidad(200)
                .precioUnitario(400000)
                .pedido(pedidoGuardado)
                .producto(productoGuardado)
                .build();
        itemPedidoRepository.save(itemPedido);
        
        ItemPedido itemPedido2 = ItemPedido.builder()
                .cantidad(200)
                .precioUnitario(400000)
                .pedido(pedidoGuardado)
                .producto(productoGuardado)
                .build();
        itemPedidoRepository.save(itemPedido2);
        
        ItemPedido itemPedido3 = ItemPedido.builder()
                .cantidad(200)
                .precioUnitario(400000)
                .pedido(pedidoGuardado)
                .producto(productoGuardado)
                .build();
        itemPedidoRepository.save(itemPedido3);
        
        Optional<Integer> resultado=itemPedidoRepository.totalVentasPorProducto(producto.getId());
        
        assertThat(resultado.get()).isEqualTo(240000000);
    }
}
