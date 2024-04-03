package ventaproducto.ventasproductos.servicies.DetalleEnvio;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ventaproducto.ventasproductos.Enums.StatusPedido;
import ventaproducto.ventasproductos.dto.DetalleEnvio.DetalleEnvioDto;
import ventaproducto.ventasproductos.dto.DetalleEnvio.DetalleEnvioDtoSave;
import ventaproducto.ventasproductos.dto.DetalleEnvio.DetalleEnvioMapper;
import ventaproducto.ventasproductos.entities.DetalleEnvio;
import ventaproducto.ventasproductos.repository.DetalleEnvioRepository;


@Service
public class DetalleEnvioServicie implements DetalleEnvioInterface {
    private final DetalleEnvioRepository detalleEnvioRepository;

    public DetalleEnvioServicie(DetalleEnvioRepository detalleEnvioRepository) {
        this.detalleEnvioRepository = detalleEnvioRepository;
    }
    
    public DetalleEnvioDto guardarDetalleEnvio(DetalleEnvioDtoSave detalleEnvioJSON){
        DetalleEnvio detalleEnvio = DetalleEnvioMapper.INSTANCE.Formato(detalleEnvioJSON);
        DetalleEnvio detalleEnvioGuardado = detalleEnvioRepository.save(detalleEnvio);
        return DetalleEnvioMapper.INSTANCE.DetalleEnvioToDetalleEnvioDto(detalleEnvioGuardado);
    }

    @Override
    public DetalleEnvioDto actualizarDetalleEnvio(DetalleEnvioDtoSave detalleEnvioJSON) {
        DetalleEnvio detalleEnvio = DetalleEnvioMapper.INSTANCE.Formato(detalleEnvioJSON);
        DetalleEnvio detalleEnvioExiste = detalleEnvioRepository.findById(detalleEnvioJSON.id()).orElseThrow(()-> new RuntimeException("||| No se encontró detalle del envio ||||"));
        detalleEnvioExiste.setNumeroGuia(detalleEnvio.getNumeroGuia());
        detalleEnvioExiste.setDireccion(detalleEnvio.getDireccion());
        detalleEnvioExiste.setTransportadora(detalleEnvio.getTransportadora());
        detalleEnvioExiste.setPedido(detalleEnvio.getPedido());

        detalleEnvioExiste = detalleEnvioRepository.save(detalleEnvioExiste);

        return DetalleEnvioMapper.INSTANCE.DetalleEnvioToDetalleEnvioDto(detalleEnvioExiste);
    }

    @Override
    public Optional<List<DetalleEnvioDto>> getDestalleEnvio() {
        List<DetalleEnvio> detalleEnvio = detalleEnvioRepository.findAll();
        List<DetalleEnvioDto> detalleEnvioDtos=detalleEnvio.stream().map(DetalleEnvioMapper.INSTANCE::DetalleEnvioToDetalleEnvioDto).collect(Collectors.toList());
        return Optional.of(detalleEnvioDtos);
    }

    @Override
    public DetalleEnvioDto findById(Long id) {
        DetalleEnvio detalleEnvio = detalleEnvioRepository.findById(id).orElseThrow(()-> new RuntimeException("||| No se encontró detalle del envio ||||"));
        return DetalleEnvioMapper.INSTANCE.DetalleEnvioToDetalleEnvioDto(detalleEnvio);
    }

    @Override
    public void eliminarDetalleEnvio(Long id) {
        DetalleEnvio detalleEnvio = detalleEnvioRepository.findById(id).orElseThrow(()-> new RuntimeException("||| No se encontró detalle del envio ||||"));
        detalleEnvioRepository.delete(detalleEnvio);
    }

    @Override
    public Optional<DetalleEnvioDto> findByPedidoId(Long pedidoId) {
        DetalleEnvio detalleEnvio = detalleEnvioRepository.findByPedidoId(pedidoId)
                .orElseThrow(()->new RuntimeException("||| No se encontró detalle del envio ||||"));
        return Optional.of(DetalleEnvioMapper.INSTANCE.DetalleEnvioToDetalleEnvioDto(detalleEnvio));
    }

    @Override
    public Optional<List<DetalleEnvioDto>> findByTransportadora(String transportadora) {
        List<DetalleEnvio> detalleEnvios = detalleEnvioRepository.findByTransportadora(transportadora)
                .orElseThrow(()-> new RuntimeException("||| No se encontró detalle del envio ||||"));
        List<DetalleEnvioDto> detalleEnvioDtos = detalleEnvios.stream().map(DetalleEnvioMapper.INSTANCE::DetalleEnvioToDetalleEnvioDto)
                .collect(Collectors.toList());
        return Optional.of(detalleEnvioDtos);
    }

    @Override
    public Optional<List<DetalleEnvioDto>> findByEstado(StatusPedido estado) {
        List<DetalleEnvio> detalleEnvios = detalleEnvioRepository.findByestado(estado)
                .orElseThrow(()-> new RuntimeException("||| No se encontró detalle del envio ||||"));
        List<DetalleEnvioDto> detalleEnvioDtos = detalleEnvios.stream().map(DetalleEnvioMapper.INSTANCE::DetalleEnvioToDetalleEnvioDto)
                .collect(Collectors.toList());

        return Optional.of(detalleEnvioDtos);
    }
}

