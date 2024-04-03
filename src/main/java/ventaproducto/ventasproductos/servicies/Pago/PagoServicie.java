package ventaproducto.ventasproductos.servicies.Pago;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ventaproducto.ventasproductos.Enums.MetodoPago;
import ventaproducto.ventasproductos.dto.Pago.PagoDto;
import ventaproducto.ventasproductos.dto.Pago.PagoDtoSave;
import ventaproducto.ventasproductos.dto.Pago.PagoMapper;
import ventaproducto.ventasproductos.entities.Pago;
import ventaproducto.ventasproductos.repository.PagoRepository;

@Transactional
@Service
public class PagoServicie implements PagoServicieInterface {
    
    private final PagoRepository pagoRepository;

    public PagoServicie(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }

    @Override
    public PagoDto guardarPago(PagoDtoSave PagoJSON) {
        Pago pago = PagoMapper.INSTANCE.Formato(PagoJSON);
        Pago pagoGuardado = pagoRepository.save(pago);
        return PagoMapper.INSTANCE.PagoToPagoDto(pagoGuardado);
    }

    @Override
    public PagoDto actualizarPago(Long id,PagoDtoSave PagoJSON) {
        Pago pagoo = PagoMapper.INSTANCE.Formato(PagoJSON);
        Pago pagoExiste = pagoRepository.findById(PagoJSON.id()).orElseThrow(()-> new RuntimeException("||| Pago No Encontrado |||"));
        pagoExiste.setMetodoPago(pagoo.getMetodoPago());
        pagoExiste.setFechaPago(pagoo.getFechaPago());
        pagoExiste.setPedido(pagoo.getPedido());
        
        Pago pagoGuardado=pagoRepository.save(pagoExiste);

        return PagoMapper.INSTANCE.PagoToPagoDto(pagoGuardado);
    }

    @Override
    public Optional<List<PagoDto>> getAllPago() {
        List<Pago> pago = pagoRepository.findAll();
        List<PagoDto> pagoDto = pago.stream().map(PagoMapper.INSTANCE::PagoToPagoDto).collect(Collectors.toList());
        return Optional.of(pagoDto);
    }

    @Override
    public PagoDto findById(Long id) {
        Pago pago = pagoRepository.findById(id).orElseThrow(()->new RuntimeException(" ||| Pago No Encontrado |||"));
        return PagoMapper.INSTANCE.PagoToPagoDto(pago);
    }

    @Override
    public void EliminarPAgo(Long id) {
        Pago pago = pagoRepository.findById(id).orElseThrow(()->new RuntimeException("Pago No Encontrado"));
        pagoRepository.delete(pago);
    }

    @Override
    public Optional<List<PagoDto>> findByFechaPagoBetween(LocalDateTime fechaInicio, LocalDateTime fechaFinal) {
        List<Pago> pago = pagoRepository.findByFechaPagoBetween(fechaInicio,fechaFinal).orElseThrow(()->new RuntimeException("Pago No Encontrado"));
        List<PagoDto> pagoDto = pago.stream().map(PagoMapper.INSTANCE::PagoToPagoDto).collect(Collectors.toList());
        return Optional.of(pagoDto);
    }

    @Override
    public Optional<PagoDto> findByIdAndMetodoPago(Long Id, MetodoPago metodoPago) {
        Pago pago = pagoRepository.findByIdAndMetodoDePago(Id,metodoPago).orElseThrow(()->new RuntimeException("Pago No Encontrado"));
        return Optional.ofNullable(PagoMapper.INSTANCE.PagoToPagoDto(pago));
    }

}
