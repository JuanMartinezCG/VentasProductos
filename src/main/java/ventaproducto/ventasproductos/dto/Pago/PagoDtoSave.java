package ventaproducto.ventasproductos.dto.Pago;

import java.time.LocalDateTime;

import ventaproducto.ventasproductos.dto.Pedido.PedidoDto;

public record PagoDtoSave(
        long id,
        String metodoPago,
        LocalDateTime fechaPago,
        PedidoDto pedidoDto) {
    
}
