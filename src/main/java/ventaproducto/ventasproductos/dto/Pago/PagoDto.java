package ventaproducto.ventasproductos.dto.Pago;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import ventaproducto.ventasproductos.Enums.MetodoPago;
import ventaproducto.ventasproductos.dto.Pedido.PedidoDto;

public record PagoDto(
            long id,
            PedidoDto pedido,
            BigDecimal totalPago,
            LocalDateTime fechaPago,
            MetodoPago metodoPago){

}
