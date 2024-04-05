package ventaproducto.ventasproductos.dto.DetalleEnvio;

import ventaproducto.ventasproductos.dto.Pedido.PedidoDto;

public record DetalleEnvioDtoSave (
        long id,
        String direccion,
        String transportadora,
        String numeroGuia,
        PedidoDto pedidoDto){
    
}
