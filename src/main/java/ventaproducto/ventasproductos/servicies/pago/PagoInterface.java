package ventaproducto.ventasproductos.servicies.pago;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import ventaproducto.ventasproductos.Enums.MetodoPago;
import ventaproducto.ventasproductos.dto.Pago.PagoDto;
import ventaproducto.ventasproductos.dto.Pago.PagoDtoSave;

public interface PagoInterface {
    
    PagoDto guardarPago(PagoDtoSave pago);

    PagoDto actualizarPago(Long id,PagoDtoSave pagoDto);

    Optional<List<PagoDto>> getAllPago();

    PagoDto findById(Long id);

    void eliminarPAgo(Long id);

    Optional<List<PagoDto>> findByFechaPagoBetween(LocalDateTime fechaInicio, LocalDateTime fechaFinal);

    Optional<PagoDto> findByIdAndMetodoDePago(Long Id, MetodoPago metodoDePago);
    
    
}
