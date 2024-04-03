package ventaproducto.ventasproductos.Servicies.DetalleEnvio;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import ventaproducto.ventasproductos.Enums.StatusPedido;
import ventaproducto.ventasproductos.dto.DetalleEnvio.DetalleEnvioMapper;
import ventaproducto.ventasproductos.dto.DetalleEnvio.DetalleEnvioMapperImpl;
import ventaproducto.ventasproductos.entities.DetalleEnvio;
import ventaproducto.ventasproductos.entities.Pedido;
import ventaproducto.ventasproductos.repository.DetalleEnvioRepository;
import ventaproducto.ventasproductos.repository.PedidoRepository;
import ventaproducto.ventasproductos.servicies.DetalleEnvio.DetalleEnvioServicie;
import ventaproducto.ventasproductos.servicies.Pedido.PedidoServicie;

@RunWith(MockitoJUnitRunner.class)
public class DetalleEnvioServiceTest {
    
    @Mock
    private DetalleEnvioRepository detalleEnvioRepository;
    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private DetalleEnvioServicie detalleEnvioService;
    @InjectMocks
    private PedidoServicie pedidoService;

    DetalleEnvio detalleEnvio;
    Pedido pedido = new Pedido();;
    DetalleEnvioMapper detalleEnvioMapper;

    @BeforeEach
    void setUp() {
        pedidoService = new PedidoServicie(pedidoRepository);
        pedido=new Pedido();
        pedido.setId(1L);
        pedido.setPago(null);
        pedido.setItemPedidos(null);
        pedido.setDetalleEnvio(null);
        pedido.setStatusPedido(StatusPedido.PENDIENTE);
        pedido.setCliente(null);
        pedido.setFechaPedido(LocalDateTime.of(2020,11,12,12,12));
        pedidoRepository.save(pedido);

        detalleEnvioMapper=new DetalleEnvioMapperImpl();
        detalleEnvioService= new DetalleEnvioServicie(detalleEnvioRepository);
        detalleEnvio=new DetalleEnvio();
        detalleEnvio.setId(1L);
        detalleEnvio.setNumeroGuia("123456");
        detalleEnvio.setTransportadora("testtransportadora1");
        detalleEnvio.setDireccion("dereccion1test");
        detalleEnvio.setPedido(pedido);
        detalleEnvioRepository.save(detalleEnvio);
    }
}
