package ventaproducto.ventasproductos.dto.ItemPedido;

import java.math.BigDecimal;

import ventaproducto.ventasproductos.dto.Pedido.PedidoDto;
import ventaproducto.ventasproductos.dto.Producto.ProductoDto;

public record ItemPedidoDto(
            long id,
            PedidoDto pedidoDto,
            ProductoDto productoDto,
            int cantidad,
            BigDecimal precioUnitario) {

}