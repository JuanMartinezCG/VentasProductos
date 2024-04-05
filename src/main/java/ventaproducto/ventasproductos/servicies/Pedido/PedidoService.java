package ventaproducto.ventasproductos.servicies.Pedido;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ventaproducto.ventasproductos.Enums.StatusPedido;
import ventaproducto.ventasproductos.dto.Cliente.ClienteDto;
import ventaproducto.ventasproductos.dto.Cliente.ClienteMapper;
import ventaproducto.ventasproductos.dto.Pedido.PedidoDto;
import ventaproducto.ventasproductos.dto.Pedido.PedidoDtoSave;
import ventaproducto.ventasproductos.dto.Pedido.PedidoMapper;
import ventaproducto.ventasproductos.entities.Cliente;
import ventaproducto.ventasproductos.entities.Pedido;
import ventaproducto.ventasproductos.repository.PedidoRepository;

@Service
public class PedidoService implements PedidoInterface {
    
    private final PedidoRepository pedidoRepository;

    
    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public PedidoDto guardarPedido(PedidoDtoSave pedidoJSON) {
        Pedido pedido= PedidoMapper.INSTANCE.Formato(pedidoJSON);
        Pedido pedidoGuardado=pedidoRepository.save(pedido);
        return PedidoMapper.INSTANCE.PedidoToPedidoDto(pedidoGuardado);
    }

    @Override
    public PedidoDto actualizarPedido(PedidoDtoSave pedidoJSON) {
        Pedido pedido= PedidoMapper.INSTANCE.Formato(pedidoJSON);
        Pedido pedidoExiste=pedidoRepository.findById(pedidoJSON.id())
        .orElseThrow(() -> new RuntimeException(" ||| pedido no encontrado ||| "));
        pedidoExiste.setFechaPedido(pedido.getFechaPedido());
        pedidoExiste.setEstatusPedido(pedido.getEstatusPedido());
        pedidoExiste.setCliente(pedido.getCliente());
        pedidoExiste=pedidoRepository.save(pedidoExiste);
        return PedidoMapper.INSTANCE.PedidoToPedidoDto(pedidoExiste);
    }

    @Override
    public Optional<List<PedidoDto>> getAllPedido() {
        List<Pedido> pedido=pedidoRepository.findAll();
        List<PedidoDto> pedidoDto=pedido.stream().map(PedidoMapper.INSTANCE::PedidoToPedidoDto).collect(Collectors.toList());
        return Optional.of(pedidoDto);
    }

    @Override
    public PedidoDto findById(Long id) {
        Pedido pedido=pedidoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException(" ||| pedido no encontrado ||| "));
        return PedidoMapper.INSTANCE.PedidoToPedidoDto(pedido);
    }

    @Override
    public void EliminarPeido(Long id) {
        Pedido pedido=pedidoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException(" ||| pedido no encontrado ||| "));
        pedidoRepository.delete(pedido);
    }

    @Override
    public Optional<List<PedidoDto>> findByFechaPedidoBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
       List<Pedido> pedidos= pedidoRepository.findByFechaPedidoBetween(fechaInicio,fechaFin)
       .orElseThrow(() -> new RuntimeException(" ||| pedido no encontrado ||| "));
       List<PedidoDto>pedidoDtos=pedidos.stream().map(PedidoMapper.INSTANCE::PedidoToPedidoDto)
               .collect(Collectors.toList());

        return Optional.of(pedidoDtos);
    }

    @Override
    public Optional<List<PedidoDto>> findByClienteAndEstado(Long clienteId, StatusPedido estatusPedido) {
        List<Pedido> pedidos = pedidoRepository.findByClienteAndstatusPedido(clienteId,estatusPedido)
        .orElseThrow(() -> new RuntimeException(" ||| pedido no encontrado ||| "));
        List<PedidoDto>pedidoDtos=pedidos.stream().map(PedidoMapper.INSTANCE::PedidoToPedidoDto).collect(Collectors.toList());

        return Optional.of(pedidoDtos);
    }

    @Override
    public Optional<List<PedidoDto>> findByClienteWhithItemPedidos(ClienteDto clienteJSON) {
        Cliente cliente = ClienteMapper.INSTANCE.DtoToCliente(clienteJSON);
       List<Pedido>pedidos=pedidoRepository.findByClienteWhithItemPedidos(cliente)
       .orElseThrow(() -> new RuntimeException(" ||| pedido no encontrado ||| "));
       List<PedidoDto> pedidoDtos = pedidos.stream().map(PedidoMapper.INSTANCE::PedidoToPedidoDto)
               .collect(Collectors.toList());
        return Optional.of(pedidoDtos);
    }
}
