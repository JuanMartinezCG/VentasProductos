package ventaproducto.ventasproductos.dto.Pedido;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import ventaproducto.ventasproductos.Enums.StatusPedido;
import ventaproducto.ventasproductos.dto.Cliente.ClienteDto;
import ventaproducto.ventasproductos.dto.DetalleEnvio.DetalleEnvioDto;
import ventaproducto.ventasproductos.dto.ItemPedido.ItemPedidoDto;
import ventaproducto.ventasproductos.dto.Pago.PagoDto;

public record PedidoDto (
            long id,
            LocalDateTime fechaPedido,
            StatusPedido estatusPedido,
            ClienteDto cliente,
            List<ItemPedidoDto> itemPedidoDtos,
            DetalleEnvioDto detalleEnvioDto,
            PagoDto pagoDto){

    public List<ItemPedidoDto> itemPedidoDtos(){
        return Collections.unmodifiableList(itemPedidoDtos);
    }
}
