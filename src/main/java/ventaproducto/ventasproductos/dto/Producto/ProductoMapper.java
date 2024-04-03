package ventaproducto.ventasproductos.dto.Producto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import ventaproducto.ventasproductos.entities.Producto;

@Mapper
public interface ProductoMapper {
    ProductoMapper INSTANCE = Mappers.getMapper(ProductoMapper.class);

    ProductoDto ProductoToProductoDto(Producto productoDto);
    Producto DtoToProducto(ProductoDto producto);
    Producto Formato(ProductoDtoSave producto);
}
