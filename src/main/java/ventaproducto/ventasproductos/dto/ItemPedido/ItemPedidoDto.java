package ventaproducto.ventasproductos.dto.ItemPedido;

import java.math.BigDecimal;

import ventaproducto.ventasproductos.dto.Pedido.PedidoDto;
import ventaproducto.ventasproductos.dto.Producto.ProcductoDto;

public record ItemPedidoDto(
    long id,
    PedidoDto pedido,
    ProcductoDto producto,
    int cantidad,
    BigDecimal precioUnitario){
}
