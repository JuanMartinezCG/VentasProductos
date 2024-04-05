package ventaproducto.ventasproductos.servicies.Producto;

import java.util.List;
import java.util.Optional;

import ventaproducto.ventasproductos.dto.Producto.ProductoDto;
import ventaproducto.ventasproductos.dto.Producto.ProductoDtoSave;

public interface ProductoInterface {

    ProductoDto guardarProducto(ProductoDtoSave producto);

    ProductoDto actualizarProducto(ProductoDtoSave producto);

    Optional<List<ProductoDto>> getProducto();

    ProductoDto findById(Long id);

    void EliminarProducto(Long id);

    Optional<List<ProductoDto>> findByNombreContainingIgnoreCase(String termino);

    Optional<List<ProductoDto>> findByProductStockEqual(Integer num);

    Optional<List<ProductoDto>> findByPrecioAndStock(Integer precio, Integer stock);
}
