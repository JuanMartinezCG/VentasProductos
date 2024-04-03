package ventaproducto.ventasproductos.dto.DetalleEnvio;

import ventaproducto.ventasproductos.dto.Pedido.PedidoDto;

public record DetalleEnvioDto(
        long id,
        PedidoDto pedido,
        String direccion,
        String transportadora,
        String numeroGuia){

}
