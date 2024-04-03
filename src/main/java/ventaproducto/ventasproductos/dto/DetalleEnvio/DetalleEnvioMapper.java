package ventaproducto.ventasproductos.dto.DetalleEnvio;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import ventaproducto.ventasproductos.entities.DetalleEnvio;

@Mapper
public interface DetalleEnvioMapper {
    DetalleEnvioMapper INSTANCE = Mappers.getMapper(DetalleEnvioMapper.class);

    DetalleEnvioDto DetalleEnvioToDetalleEnvioDto(DetalleEnvio detalleEnvioDto);
    DetalleEnvio DtoToDetalleEnvio(DetalleEnvioDto detalleEnvio);
    DetalleEnvio Formato(DetalleEnvioDtoSave detalleEnvio);
}
