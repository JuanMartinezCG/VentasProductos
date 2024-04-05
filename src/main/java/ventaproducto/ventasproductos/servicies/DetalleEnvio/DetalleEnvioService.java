package ventaproducto.ventasproductos.servicies.DetalleEnvio;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import ventaproducto.ventasproductos.dto.DetalleEnvio.DetalleEnvioDto;
import ventaproducto.ventasproductos.dto.DetalleEnvio.DetalleEnvioDtoSave;
import ventaproducto.ventasproductos.dto.DetalleEnvio.DetalleEnvioMapper;
import ventaproducto.ventasproductos.entities.DetalleEnvio;
import ventaproducto.ventasproductos.repository.DetalleEnvioRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class DetalleEnvioService implements DetalleEnvioInterface {
    
    private final DetalleEnvioRepository detalleEnvioRep;

    public DetalleEnvioService(DetalleEnvioRepository detalleEnvioRep) {
        this.detalleEnvioRep = detalleEnvioRep;
    }

    // -----------------------------------------------------------------------------

    @Override
    public DetalleEnvioDto guardarDetalleEnvio(DetalleEnvioDtoSave detalleEnvioJSON) {
        DetalleEnvio detalleEnvio= DetalleEnvioMapper.INSTANCE.Formato(detalleEnvioJSON);
        DetalleEnvio detalleEnvioGuardado=detalleEnvioRep.save(detalleEnvio);
        return DetalleEnvioMapper.INSTANCE.DetalleEnvioToDetalleEnvioDto(detalleEnvioGuardado);
    }
    
    @Override
    public DetalleEnvioDto actualizarDetalleEnvio(DetalleEnvioDtoSave detalleEnvioJSON) {

        DetalleEnvio detalleEnvio= DetalleEnvioMapper.INSTANCE.Formato(detalleEnvioJSON);

        DetalleEnvio detalleEnvioExistente = detalleEnvioRep.findById(detalleEnvioJSON.id())
        .orElseThrow(() -> new RuntimeException(" ||| DetalleEnvio no encontrado con ID: ||| " + detalleEnvioJSON.id()));

            detalleEnvioExistente.setNumeroGuia(detalleEnvio.getNumeroGuia());
            detalleEnvioExistente.setDireccion(detalleEnvio.getDireccion());
            detalleEnvioExistente.setTransportadora(detalleEnvio.getTransportadora());
            detalleEnvioExistente.setPedido(detalleEnvio.getPedido());
    
            detalleEnvioExistente = detalleEnvioRep.save(detalleEnvioExistente);
        return DetalleEnvioMapper.INSTANCE.DetalleEnvioToDetalleEnvioDto(detalleEnvioExistente);
    }
    
    @Override
    public Optional<List<DetalleEnvioDto>> getAllDestalleEnvio() {
        List<DetalleEnvio> detalleEnvio=detalleEnvioRep.findAll();
        List<DetalleEnvioDto> detalleEnvioDtos=detalleEnvio.stream()
        .map(DetalleEnvioMapper.INSTANCE::DetalleEnvioToDetalleEnvioDto).collect(Collectors.toList());
        return Optional.of(detalleEnvioDtos);
    }

    @Override
    public DetalleEnvioDto findById(Long id) {
        DetalleEnvio detalleEnvio=detalleEnvioRep.findById(id)
        .orElseThrow(() -> new RuntimeException(" ||| DetalleEnvio no encontrado con ID: ||| "));
        return DetalleEnvioMapper.INSTANCE.DetalleEnvioToDetalleEnvioDto(detalleEnvio);
    }

    @Override
    public void EliminarDetalleEnvio(Long id) {
        DetalleEnvio detalleEnvio=detalleEnvioRep.findById(id)
        .orElseThrow(() -> new RuntimeException(" ||| DetalleEnvio no encontrado con ID: ||| "));
        detalleEnvioRep.delete(detalleEnvio);
    }

    @Override
    public Optional<DetalleEnvioDto> findByPedidoId(Long pedidoId) {
        DetalleEnvio detalleEnvio= detalleEnvioRep.findByPedidoId(pedidoId)
        .orElseThrow(() -> new RuntimeException(" ||| DetalleEnvio no encontrado con ID: ||| "));
        return Optional.of(DetalleEnvioMapper.INSTANCE.DetalleEnvioToDetalleEnvioDto(detalleEnvio));
    }

    @Override
    public Optional<List<DetalleEnvioDto>> findByTransportadora(String transportadora) {
        List<DetalleEnvio> detalleEnvios= detalleEnvioRep.findByTransportadora(transportadora)
        .orElseThrow(() -> new RuntimeException(" ||| DetalleEnvio no encontrado con ID: ||| "));
        List<DetalleEnvioDto> detalleEnvioDtos= detalleEnvios.stream().map(DetalleEnvioMapper.INSTANCE::DetalleEnvioToDetalleEnvioDto)
                .collect(Collectors.toList());
        return Optional.of(detalleEnvioDtos);
    }
}
