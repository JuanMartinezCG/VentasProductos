package ventaproducto.ventasproductos.dto.ItemPedido;

import ventaproducto.ventasproductos.dto.Producto.ProductoDto;

public record ItemPedidoDtoSave(
        long id,
        ProductoDto productoDto,
        int cantidad,
        int precioUnitario) {
    
}
