package ventaproducto.ventasproductos.dto.ItemPedido;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import ventaproducto.ventasproductos.dto.Pedido.PedidoDto;
import ventaproducto.ventasproductos.dto.Producto.ProcductoDto;

@Getter
@Setter
public class ItemPedidoDto {
    private long id;
    private PedidoDto pedido;
    private ProcductoDto producto;
    private int cantidad;
    private BigDecimal precioUnitario;
}
