package ventaproducto.ventasproductos.servicies.Pedido;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ventaproducto.ventasproductos.Enums.StatusPedido;
import ventaproducto.ventasproductos.dto.Pedido.PedidoDto;
import ventaproducto.ventasproductos.dto.Pedido.PedidoDtoSave;
import ventaproducto.ventasproductos.dto.Pedido.PedidoMapper;
import ventaproducto.ventasproductos.entities.Pedido;
import ventaproducto.ventasproductos.repository.PedidoRepository;



@Service
public class PedidoServicie implements PedidoServicieInterface{
    
    private final PedidoRepository pedidoRepository;

    public PedidoServicie(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }
    
    @Override
    public PedidoDto guardarPedido(PedidoDtoSave pedidoJSON) {
        Pedido pedido = PedidoMapper.INSTANCE.Formato(pedidoJSON);
        Pedido pedidoGuardado = pedidoRepository.save(pedido);
        return PedidoMapper.INSTANCE.PedidoToPedidoDto(pedidoGuardado);
    }

    @Override
    public PedidoDto actualizarPedido(PedidoDtoSave pedidoJSON) {
        Pedido pedido = PedidoMapper.INSTANCE.Formato(pedidoJSON);
        Pedido pedidoExiste = pedidoRepository.findById(pedidoJSON.id())
        .orElseThrow(()->new RuntimeException("||| Pedido No Encontrado |||"));
        pedidoExiste.setFechaPedido(pedido.getFechaPedido());
        pedidoExiste.setStatusPedido(pedido.getStatusPedido());
        pedidoExiste.setCliente(pedido.getCliente());
        pedidoExiste=pedidoRepository.save(pedidoExiste);
        return PedidoMapper.INSTANCE.PedidoToPedidoDto(pedidoExiste);
    }

    @Override
    public Optional<List<PedidoDto>> getAllPedido() {
        List<Pedido> pedido = pedidoRepository.findAll();
        List<PedidoDto> pedidoDto = pedido.stream().map(PedidoMapper.INSTANCE::PedidoToPedidoDto).collect(Collectors.toList());
        return Optional.of(pedidoDto);
    }

    @Override
    public PedidoDto findById(Long id) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(()->new RuntimeException("||| Pedido No Encontrado |||"));
        return PedidoMapper.INSTANCE.PedidoToPedidoDto(pedido);
    }

    @Override
    public void EliminarPedido(Long id) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(()->new RuntimeException("||| Pedido No Encontrado |||"));
        pedidoRepository.delete(pedido);
    }

    @Override
    public Optional<List<PedidoDto>> findByFechaPedidoBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
       List<Pedido> pedidos = pedidoRepository.findByFechaPedidoBetween(fechaInicio,fechaFin);
    //    .orElseThrow(()-> new RuntimeException(" ||| No se encontaron pedidos en dichas fechas ||| "));
       List<PedidoDto>pedidoDtos=pedidos.stream().map(PedidoMapper.INSTANCE::PedidoToPedidoDto)
               .collect(Collectors.toList());

        return Optional.of(pedidoDtos);
    }

    @Override
    public Optional<List<PedidoDto>> findByClienteAndEstado(Long IdCliente, StatusPedido estadoPedido) {
        List<Pedido> pedidos = pedidoRepository.findByClienteAndStatusPedido(IdCliente, estadoPedido);
            //   .orElseThrow(()-> new RuntimeException("||| Pedido No Encontrado |||"));
        List<PedidoDto>pedidoDtos=pedidos.stream().map(PedidoMapper.INSTANCE::PedidoToPedidoDto).collect(Collectors.toList());
        return Optional.of(pedidoDtos);
    }
}
