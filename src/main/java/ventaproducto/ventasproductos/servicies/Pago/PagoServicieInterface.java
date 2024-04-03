package ventaproducto.ventasproductos.servicies.Pago;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import ventaproducto.ventasproductos.Enums.MetodoPago;
import ventaproducto.ventasproductos.dto.Pago.PagoDto;
import ventaproducto.ventasproductos.dto.Pago.PagoDtoSave;

public interface PagoServicieInterface {

    PagoDto guardarPago(PagoDtoSave pago);

    PagoDto actualizarPago(Long id,PagoDtoSave pago);

    Optional<List<PagoDto>> getAllPago();

    PagoDto findById(Long id);

    void EliminarPAgo(Long id);

    Optional<List<PagoDto>> findByFechaPagoBetween(LocalDateTime fechaInicio, LocalDateTime fechaFinal);

    Optional<PagoDto> findByIdAndMetodoPago(Long Id, MetodoPago metodoDePago);

    

}
