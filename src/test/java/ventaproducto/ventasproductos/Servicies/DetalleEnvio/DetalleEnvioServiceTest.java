package ventaproducto.ventasproductos.Servicies.DetalleEnvio;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import ventaproducto.ventasproductos.Enums.StatusPedido;
import ventaproducto.ventasproductos.dto.DetalleEnvio.DetalleEnvioDto;
import ventaproducto.ventasproductos.dto.DetalleEnvio.DetalleEnvioDtoSave;
import ventaproducto.ventasproductos.dto.DetalleEnvio.DetalleEnvioMapper;
import ventaproducto.ventasproductos.dto.DetalleEnvio.DetalleEnvioMapperImpl;
import ventaproducto.ventasproductos.dto.Pedido.PedidoDto;
import ventaproducto.ventasproductos.dto.Pedido.PedidoDtoSave;
import ventaproducto.ventasproductos.entities.DetalleEnvio;
import ventaproducto.ventasproductos.entities.Pedido;
import ventaproducto.ventasproductos.repository.DetalleEnvioRepository;
import ventaproducto.ventasproductos.repository.PedidoRepository;
import ventaproducto.ventasproductos.servicies.DetalleEnvio.DetalleEnvioService;
import ventaproducto.ventasproductos.servicies.Pedido.PedidoService;

@ExtendWith(MockitoExtension.class)
public class DetalleEnvioServiceTest {
    
    @Mock
    private DetalleEnvioRepository detalleEnvioRepository;
    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private DetalleEnvioService detalleEnvioService;
    @InjectMocks
    private PedidoService pedidoService;

    DetalleEnvio detalleEnvio;
    Pedido pedido;
    DetalleEnvioMapper detalleEnvioMapper;
    
    @BeforeEach
    void setUp(){
        pedidoService=new PedidoService(pedidoRepository);
        pedido=new Pedido();
        pedido.setId(1L);
        pedido.setPago(null);
        pedido.setItemPedidos(null);
        pedido.setDetalleEnvio(null);
        pedido.setEstatusPedido(StatusPedido.PENDIENTE);
        pedido.setCliente(null);
        pedido.setFechaPedido(LocalDateTime.of(2020,11,12,12,12));
        pedidoRepository.save(pedido);

        detalleEnvioMapper=new DetalleEnvioMapperImpl();
        detalleEnvioService= new DetalleEnvioService(detalleEnvioRepository);
        detalleEnvio=new DetalleEnvio();
        detalleEnvio.setId(1L);
        detalleEnvio.setNumeroGuia("123444");
        detalleEnvio.setTransportadora("testtransportadora1");
        detalleEnvio.setDireccion("dereccion1test");
        detalleEnvio.setPedido(pedido);
        detalleEnvioRepository.save(detalleEnvio);
    }
    
    @Test
    void GuardarDetalleEnvio() {
        pedidoService=new PedidoService(pedidoRepository);
        pedido = Pedido.builder()
        .id(1L)
        .pago(null)
        .itemPedidos(null)
        .detalleEnvio(null)
        .estatusPedido(StatusPedido.PENDIENTE)
        .cliente(null)
        .fechaPedido(LocalDateTime.of(2020,11,12,12,12)).build();
        pedidoRepository.save(pedido);

        PedidoDtoSave pedidoGuardado= new PedidoDtoSave(1L, null, LocalDateTime.of(2020,11,12,12,12), StatusPedido.PENDIENTE);
                
        pedidoService.guardarPedido(pedidoGuardado);

        detalleEnvioMapper=new DetalleEnvioMapperImpl();
        detalleEnvioService= new DetalleEnvioService(detalleEnvioRepository);
        detalleEnvio= DetalleEnvio.builder()
        .id(1L)
        .numeroGuia("125FT59")
        .transportadora("Rappi")
        .direccion("cra 26 casa 83")
        .pedido(pedido).build();
        detalleEnvioRepository.save(detalleEnvio);

        given(detalleEnvioRepository.save(any())).willReturn(detalleEnvio);
        
        DetalleEnvioDtoSave detalleEnvioGuardado = new DetalleEnvioDtoSave(
            1l,
            "cra 26 casa 83",
            "Rappi",
            "125FT59",
            null);
        
        
        DetalleEnvioDto detalleEnvioDto=detalleEnvioService.guardarDetalleEnvio(detalleEnvioGuardado);
        
        assertThat(detalleEnvioDto).isNotNull();
        assertThat(detalleEnvioDto.id()).isEqualTo(1L);
    }
    
    @Test
    void BuscarDetalleEnvioPorID() {
        Long detalleEnvioID=1L;
        
        given(detalleEnvioRepository.findById(detalleEnvioID)).willReturn(Optional.of(detalleEnvio));
        
        DetalleEnvioDto detalleEnvioDto=detalleEnvioService.findById(detalleEnvioID);
        
        assertThat(detalleEnvioDto).isNotNull();
    }

    @Test
    void GiveDetalleEnvioId_whenFindDetalleEnvioById_thenReturnDetalleEnvioException() {
        
        given(detalleEnvioRepository.findById(any())).willReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> detalleEnvioService.findById(1L));
    }

    @Test
    void ActualizarDetalleEnvio() {
        detalleEnvio= DetalleEnvio.builder()
        .id(1L)
        .numeroGuia("125FT59")
        .transportadora("Rappi")
        .direccion("cra 26 casa 83")
        .pedido(pedido).build();
        given(detalleEnvioRepository.save(any())).willReturn(detalleEnvio);

        DetalleEnvioDtoSave detalleEnvioGuardado = new DetalleEnvioDtoSave(
            1l,
            "cra 26 casa 83",
            "Rappi",
            "125FT59",
            null);
        detalleEnvioService.guardarDetalleEnvio(detalleEnvioGuardado);

        detalleEnvio.setNumeroGuia("125FT59");
        DetalleEnvioDto detalleEnvioDto=detalleEnvioService.guardarDetalleEnvio(detalleEnvioGuardado);
        
        assertThat(detalleEnvioDto).isNotNull();
        assertThat(detalleEnvioDto.id()).isEqualTo(1L);
    }

    @Test
    void getAllDestalleEnvio() {
        Optional<List<DetalleEnvioDto>> detalleEnvioDto=detalleEnvioService.getAllDestalleEnvio();
        assertThat(detalleEnvioDto).isNotNull();
    }

    @Test
    void BorraDetalleEnvioPorId() {
        
        given(detalleEnvioRepository.findById(1L)).willReturn(Optional.of(detalleEnvio));
        willDoNothing().given(detalleEnvioRepository).delete(detalleEnvio);
        
        detalleEnvioService.EliminarDetalleEnvio(1L);
        
        verify(detalleEnvioRepository, times(1)).delete(detalleEnvio);
    }

    @Test
    void BuscaporID_de_Pedido_thenReturnDetalleEnvio() {

        pedido = Pedido.builder()
        .id(1L)
        .pago(null)
        .itemPedidos(null)
        .detalleEnvio(null)
        .estatusPedido(StatusPedido.PENDIENTE)
        .cliente(null)
        .fechaPedido(LocalDateTime.of(2020,11,12,12,12)).build();
        pedidoRepository.save(pedido);

        detalleEnvio.setId(1L);
        detalleEnvio.setNumeroGuia("125FT59");
        detalleEnvio.setTransportadora("Rappi");
        detalleEnvio.setDireccion("cra 26 casa 83");
        detalleEnvio.setPedido(pedido);

        given(detalleEnvioRepository.save(any())).willReturn(detalleEnvio);
        

        DetalleEnvioDtoSave detalleEnvioGuardado = new DetalleEnvioDtoSave(
            1l,
            "cra 26 casa 83",
            "Rappi",
            "125FT59",
                (new PedidoDto(1L,LocalDateTime.of(2020,11,12,12,12),StatusPedido.PENDIENTE,null,null,null,null)));
        detalleEnvioService.guardarDetalleEnvio(detalleEnvioGuardado);
        
        given(detalleEnvioRepository.findByPedidoId(detalleEnvioGuardado.pedidoDto().id())).willReturn(Optional.of(detalleEnvio));
        
        Optional<DetalleEnvioDto> detalleEnvioDto=detalleEnvioService.findByPedidoId(1L);
        
        assertThat(detalleEnvioDto).isNotNull();
    }

    @Test
    void GiveDetalleEnvio_whenFindByTransportadora_thenReturnDetalleEnvio() {
        String transportadora="Rappi";
        
        given(detalleEnvioRepository.findByTransportadora(transportadora)).willReturn(Optional.of(List.of(detalleEnvio)));
        
        Optional<List<DetalleEnvioDto>> detalleEnvioDto=detalleEnvioService.findByTransportadora(transportadora);
        
        assertThat(detalleEnvioDto).isNotNull();
    }
}
