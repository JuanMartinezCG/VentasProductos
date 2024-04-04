package ventaproducto.ventasproductos.dto.ItemPedido;

import java.math.BigDecimal;

import ventaproducto.ventasproductos.dto.Producto.ProductoDto;

public record ItemPedidoDto(
            long id,
            ProductoDto productoDto,
            int cantidad,
            BigDecimal precioUnitario) {

}