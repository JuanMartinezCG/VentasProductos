package ventaproducto.ventasproductos.servicies.pago;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ventaproducto.ventasproductos.Enums.MetodoPago;
import ventaproducto.ventasproductos.dto.Pago.PagoDto;
import ventaproducto.ventasproductos.dto.Pago.PagoDtoSave;
import ventaproducto.ventasproductos.dto.Pago.PagoMapper;
import ventaproducto.ventasproductos.entities.Pago;
import ventaproducto.ventasproductos.repository.PagoRepository;

@Service
public class PagoService implements PagoInterface{

    private final PagoRepository pagoRepository;

    public PagoService(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }

    @Override
    public PagoDto guardarPago(PagoDtoSave pagoJSON) {
        Pago pago = PagoMapper.INSTANCE.Formato(pagoJSON);
        Pago pagoGuardado=pagoRepository.save(pago);
        return PagoMapper.INSTANCE.PagoToPagoDto(pagoGuardado);
    }

    @Override
    public PagoDto actualizarPago(Long id,PagoDtoSave pagoJSON) {
        Pago pago= PagoMapper.INSTANCE.Formato(pagoJSON);
        Pago pagoExiste=pagoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException(" ||| pago no encontrado con ID: ||| "));
        pagoExiste.setTotalPago(pago.getTotalPago());
        pagoExiste.setMetodoPago(pago.getMetodoPago());
        pagoExiste.setFechaPago(pago.getFechaPago());
        pagoExiste.setPedido(pago.getPedido());

        Pago pagoGuardado=pagoRepository.save(pagoExiste);

        return PagoMapper.INSTANCE.PagoToPagoDto(pagoGuardado);
    }

    @Override
    public Optional<List<PagoDto>> getAllPago() {
        List<Pago> pago=pagoRepository.findAll();
        List<PagoDto> pagoDto=pago.stream()
        .map(PagoMapper.INSTANCE::PagoToPagoDto).collect(Collectors.toList());
        return Optional.of(pagoDto);
    }

    @Override
    public PagoDto findById(Long id) {
        Pago pago = pagoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException(" ||| pago no encontrado con ID: ||| "));
        return PagoMapper.INSTANCE.PagoToPagoDto(pago);
    }

    @Override
    public void eliminarPAgo(Long id) {
        Pago pago = pagoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException(" ||| pago no encontrado con ID: ||| "));
        pagoRepository.delete(pago);
    }

    @Override
    public Optional<List<PagoDto>> findByFechaPagoBetween(LocalDateTime fechaInicio, LocalDateTime fechaFinal) {
        List<Pago> pago = pagoRepository.findByFechaPagoBetween(fechaInicio, fechaFinal)
        .orElseThrow(() -> new RuntimeException(" ||| pago no encontrado con ID: ||| "));
        List<PagoDto> pagoDto = pago.stream().map(PagoMapper.INSTANCE::PagoToPagoDto).collect(Collectors.toList());
        return Optional.of(pagoDto);
    }

    @Override
    public Optional<PagoDto> findByIdAndMetodoDePago(Long Id, MetodoPago metodoPago) {
        Pago pago=pagoRepository.findByPedidoIdAndMetodoPago(Id, metodoPago)
        .orElseThrow(() -> new RuntimeException(" ||| pago no encontrado con ID: ||| "));
        return Optional.ofNullable(PagoMapper.INSTANCE.PagoToPagoDto(pago));
    }
}

