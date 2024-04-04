package ventaproducto.ventasproductos.dto.Pago;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import ventaproducto.ventasproductos.dto.Pedido.PedidoDto;

public record PagoDtoSave(
        long id,
        BigDecimal totalPago,
        String metodoPago,
        LocalDateTime fechaPago,
        PedidoDto pedido) {  
}
