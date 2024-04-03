package ventaproducto.ventasproductos.servicies.Producto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ventaproducto.ventasproductos.dto.Producto.ProductoDto;
import ventaproducto.ventasproductos.dto.Producto.ProductoDtoSave;
import ventaproducto.ventasproductos.dto.Producto.ProductoMapper;
import ventaproducto.ventasproductos.entities.Producto;
import ventaproducto.ventasproductos.repository.ProductoRepository;

@Service
public class ProductoServicie implements ProductoServicieInterface {

    private final ProductoRepository productoRepository;

    public ProductoServicie(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public ProductoDto guardarProducto(ProductoDtoSave productoJSON) {
        Producto producto= ProductoMapper.INSTANCE.Formato(productoJSON);
        Producto productoGuardado=productoRepository.save(producto);
        return ProductoMapper.INSTANCE.ProductoToProductoDto(productoGuardado);
    }

    @Override
    public ProductoDto actualizarProducto(ProductoDtoSave productoJSON) {
        Producto producto= ProductoMapper.INSTANCE.Formato(productoJSON);
        Producto productoExiste=productoRepository.findById(productoJSON.id()).orElseThrow(()->new RuntimeException("||| Producto No encontrado |||"));
        productoExiste.setNombre(producto.getNombre());
        productoExiste.setPrice(producto.getPrice());
        productoExiste.setStock(producto.getStock());
        productoExiste=productoRepository.save(productoExiste);
        return ProductoMapper.INSTANCE.ProductoToProductoDto(productoExiste);
    }

    @Override
    public Optional<List<ProductoDto>> getAllProducto() {
        List<Producto> producto=productoRepository.findAll();
        List<ProductoDto> productoDto=producto.stream().map(ProductoMapper.INSTANCE::ProductoToProductoDto).collect(Collectors.toList());
        return Optional.of(productoDto);
    }

    @Override
    public ProductoDto findById(Long id) {
        Producto producto=productoRepository.findById(id).orElseThrow(()->new RuntimeException("||| Producto No encontrado |||"));
        return ProductoMapper.INSTANCE.ProductoToProductoDto(producto);
    }

    @Override
    public void EliminarProducto(Long id) {
        Producto producto=productoRepository.findById(id).orElseThrow(()->new RuntimeException("||| Producto No encontrado |||"));
        productoRepository.delete(producto);
    }

    @Override
    public Optional<List<ProductoDto>> findByNombreContainingIgnoreCase(String termino) {
        List<Producto> productos= productoRepository.findByNombreContainingIgnoreCase(termino)
                .orElseThrow(()-> new RuntimeException("||| Producto No encontrado |||"));
        List<ProductoDto>productoDtos=productos.stream().map(ProductoMapper.INSTANCE::ProductoToProductoDto)
                .collect(Collectors.toList());
        return Optional.of(productoDtos);
    }

    @Override
    public Optional<List<ProductoDto>> findByProductStockEqual(Integer num) {
        List<Producto> productos= productoRepository.findByProductStockEqual(num)
                .orElseThrow(()-> new RuntimeException("||| Producto No encontrado |||"));
        List<ProductoDto>productoDtos=productos.stream().map(ProductoMapper.INSTANCE::ProductoToProductoDto)
                .collect(Collectors.toList());
        return Optional.of(productoDtos);
    }

    @Override
    public Optional<List<ProductoDto>> findByPrecioAndStock(Integer precio, Integer stock) {
        List<Producto> productos= productoRepository.findByPrecioAndStock(precio,stock)
                .orElseThrow(()-> new RuntimeException(" ||| Producto No encontrado ||| "));
        List<ProductoDto>productoDtos=productos.stream().map(ProductoMapper.INSTANCE::ProductoToProductoDto)
                .collect(Collectors.toList());
        return Optional.of(productoDtos);
    }

}
