package ventaproducto.ventasproductos.dto.Producto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import ventaproducto.ventasproductos.dto.ItemPedido.ItemPedidoDto;

@Getter
@Setter
public class ProcductoDto {
    private Long id;
    private String nombre;
    private BigDecimal price;
    private int stock;

    private List<ItemPedidoDto> itemPedidoDto;
}
