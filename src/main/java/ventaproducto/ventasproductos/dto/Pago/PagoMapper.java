package ventaproducto.ventasproductos.dto.Pago;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import ventaproducto.ventasproductos.entities.Pago;

@Mapper
public interface PagoMapper {
    PagoMapper INSTANCE = Mappers.getMapper(PagoMapper.class);

    PagoDto PagoToPagoDto(Pago pagoDto);
    Pago DtoToPago(PagoDto pago);
    Pago Formato(PagoDtoSave pago);
}
