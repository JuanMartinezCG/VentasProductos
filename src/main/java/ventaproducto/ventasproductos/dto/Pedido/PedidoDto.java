package ventaproducto.ventasproductos.dto.Pedido;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import ventaproducto.ventasproductos.Enums.StatusPedido;
import ventaproducto.ventasproductos.dto.Cliente.ClienteDto;
import ventaproducto.ventasproductos.dto.ItemPedido.ItemPedidoDto;

@Getter
@Setter
public class PedidoDto {
    private long id;
    private ClienteDto cliente;
    private LocalDateTime fechaPedido;
    private StatusPedido statusPedido;

    private List<ItemPedidoDto> itemPedidos;

}
