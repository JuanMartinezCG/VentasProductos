package ventaproducto.ventasproductos.Servicies.ItemPedido;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import ventaproducto.ventasproductos.Enums.StatusPedido;
import ventaproducto.ventasproductos.entities.Cliente;
import ventaproducto.ventasproductos.entities.ItemPedido;
import ventaproducto.ventasproductos.entities.Pedido;
import ventaproducto.ventasproductos.entities.Producto;
import ventaproducto.ventasproductos.repository.ClienteRepository;
import ventaproducto.ventasproductos.repository.ItemPedidoRepository;
import ventaproducto.ventasproductos.repository.PedidoRepository;
import ventaproducto.ventasproductos.repository.ProductoRepository;

@ExtendWith(MockitoExtension.class)
public class ItemPedidoServiceTest {
    ItemPedidoRepository itemPedidoRepository;
    PedidoRepository pedidoRepository;
    ClienteRepository clienteRepository;
    ProductoRepository productoRepository;
    Cliente customer;
    Pedido pedido;
    Producto producto;
    ItemPedido itemPedido;

    @Autowired
    public ItemPedidoServiceTest(ItemPedidoRepository itemPedidoRepository, PedidoRepository pedidoRepository,
            ClienteRepository clienteRepository, ProductoRepository productoRepository) {
        this.itemPedidoRepository = itemPedidoRepository;
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.productoRepository = productoRepository;
    }

    @BeforeEach
    void setUp() {
        itemPedidoRepository.deleteAll();
        pedidoRepository.deleteAll();
        clienteRepository.deleteAll();
        productoRepository.deleteAll();
    }

}
