package ventaproducto.ventasproductos.servicies.DetalleEnvio;

import java.util.List;
import java.util.Optional;

import ventaproducto.ventasproductos.Enums.StatusPedido;
import ventaproducto.ventasproductos.dto.DetalleEnvio.DetalleEnvioDto;
import ventaproducto.ventasproductos.dto.DetalleEnvio.DetalleEnvioDtoSave;

public interface DetalleEnvioInterface {
    
    DetalleEnvioDto guardarDetalleEnvio(DetalleEnvioDtoSave detalleEnvio);

    DetalleEnvioDto actualizarDetalleEnvio(DetalleEnvioDtoSave detalleEnvio);
    
    Optional<List<DetalleEnvioDto>> getDestalleEnvio();

    DetalleEnvioDto findById(Long id);

    void eliminarDetalleEnvio(Long id);

    Optional<DetalleEnvioDto> findByPedidoId(Long pedidoId);

    Optional<List<DetalleEnvioDto>> findByTransportadora(String transportadora);

Optional<List<DetalleEnvioDto>> findByEstado(StatusPedido estado);

}
