package ventaproducto.ventasproductos.dto.DetalleEnvio;

import ventaproducto.ventasproductos.dto.Pedido.PedidoDtoSave;

public record DetalleEnvioDtoSave (
    long id,
    PedidoDtoSave pedido,
    String direccion,
    String transportadora,
    String numeroGuia){
    
}
