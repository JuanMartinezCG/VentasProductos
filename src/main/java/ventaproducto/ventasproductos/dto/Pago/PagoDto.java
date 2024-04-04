package ventaproducto.ventasproductos.dto.Pago;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import ventaproducto.ventasproductos.Enums.MetodoPago;

public record PagoDto(
            long id,
            BigDecimal totalPago,
            LocalDateTime fechaPago,
            MetodoPago metodoPago){

}
