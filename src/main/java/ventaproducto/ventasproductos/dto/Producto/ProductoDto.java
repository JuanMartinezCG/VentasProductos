package ventaproducto.ventasproductos.dto.Producto;

import java.math.BigDecimal;
import java.util.List;

import ventaproducto.ventasproductos.dto.ItemPedido.ItemPedidoDto;



public record ProductoDto(
            Long id,
            String nombre,
            BigDecimal price,
            int stock,
            List<ItemPedidoDto> itemPedidoDto){

    
}
