package ventaproducto.ventasproductos.dto.DetalleEnvio;


public record DetalleEnvioDtoSave (
        long id,
        String direccion,
        String transportadora,
        String numeroGuia){
    
}
