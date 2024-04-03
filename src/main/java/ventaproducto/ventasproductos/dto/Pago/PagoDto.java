package ventaproducto.ventasproductos.dto.Pago;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import ventaproducto.ventasproductos.Enums.MetodoPago;
import ventaproducto.ventasproductos.dto.Pedido.PedidoDto;

@Getter
@Setter
public class PagoDto {
    private long id;
    private PedidoDto pedido;
    private BigDecimal totalPago;
    private LocalDateTime fechaPago;
    private MetodoPago metodoPago;

}
