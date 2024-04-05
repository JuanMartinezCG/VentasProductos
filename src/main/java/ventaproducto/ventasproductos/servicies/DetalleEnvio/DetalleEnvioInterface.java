package ventaproducto.ventasproductos.servicies.DetalleEnvio;

import java.util.List;
import java.util.Optional;

import ventaproducto.ventasproductos.dto.DetalleEnvio.DetalleEnvioDto;
import ventaproducto.ventasproductos.dto.DetalleEnvio.DetalleEnvioDtoSave;

public interface DetalleEnvioInterface {
    
    DetalleEnvioDto guardarDetalleEnvio(DetalleEnvioDtoSave detalleEnvio);
    
    DetalleEnvioDto actualizarDetalleEnvio(DetalleEnvioDtoSave detalleEnvioDto);
    
    Optional<List<DetalleEnvioDto>> getAllDestalleEnvio();

    DetalleEnvioDto findById(Long id);

    void EliminarDetalleEnvio(Long id);

    Optional<DetalleEnvioDto> findByPedidoId(Long pedidoId);

    Optional<List<DetalleEnvioDto>> findByTransportadora(String transportadora);
    
}
