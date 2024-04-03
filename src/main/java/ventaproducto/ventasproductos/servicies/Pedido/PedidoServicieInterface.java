package ventaproducto.ventasproductos.servicies.Pedido;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import ventaproducto.ventasproductos.Enums.StatusPedido;
import ventaproducto.ventasproductos.dto.Pedido.PedidoDto;
import ventaproducto.ventasproductos.dto.Pedido.PedidoDtoSave;

public interface PedidoServicieInterface {

    PedidoDto guardarPedido(PedidoDtoSave pedido);
    
    PedidoDto actualizarPedido(PedidoDtoSave pedido);

    Optional<List<PedidoDto>> getAllPedido();

    PedidoDto findById(Long id);

    void EliminarPedido(Long id);

    Optional<List<PedidoDto>> findByFechaPedidoBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);

    Optional<List<PedidoDto>> findByClienteAndEstado(Long clienteId, StatusPedido estatusPedido);

}
