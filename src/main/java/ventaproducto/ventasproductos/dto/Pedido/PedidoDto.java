package ventaproducto.ventasproductos.dto.Pedido;

import java.time.LocalDateTime;
import java.util.List;

import ventaproducto.ventasproductos.Enums.StatusPedido;
import ventaproducto.ventasproductos.dto.Cliente.ClienteDto;
import ventaproducto.ventasproductos.dto.ItemPedido.ItemPedidoDto;


public record PedidoDto (
            long id,
            ClienteDto cliente,
            LocalDateTime fechaPedido,
            StatusPedido statusPedido,
            List<ItemPedidoDto> itemPedidos8){

}
