package ventaproducto.ventasproductos.dto.DetalleEnvio;

import lombok.Getter;
import lombok.Setter;
import ventaproducto.ventasproductos.dto.Pedido.PedidoDto;

@Getter
@Setter
public class DetalleEnvioDto {
    private long id;
    private PedidoDto pedido;
    private String direccion;
    private String transportadora;
    private String numeroGuia;

}
