package ventaproducto.ventasproductos.dto.Pedido;

import java.time.LocalDateTime;

import ventaproducto.ventasproductos.Enums.StatusPedido;
import ventaproducto.ventasproductos.dto.Cliente.ClienteDto;

public record PedidoDto (
            long id,
            ClienteDto cliente,
            LocalDateTime fechaPedido,
            StatusPedido statusPedido){

}
