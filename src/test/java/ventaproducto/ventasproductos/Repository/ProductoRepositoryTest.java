package ventaproducto.ventasproductos.Repository;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import ventaproducto.ventasproductos.AbstractIntegrationDBTest;
import ventaproducto.ventasproductos.entities.Cliente;
import ventaproducto.ventasproductos.entities.Producto;
import ventaproducto.ventasproductos.repository.ProductoRepository;

public class ProductoRepositoryTest extends AbstractIntegrationDBTest{
    
    ProductoRepository productoRepository;
    Cliente customer; 

    @Autowired
    public ProductoRepositoryTest(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @BeforeEach
    void setUp() {
        productoRepository.deleteAll();
    }

    @Test
    void GuardarProducto(){

        Producto producto = Producto.builder()
        .nombre("Cerveza Aguila")
        .stock(15)
        .price(2000).build();
        productoRepository.save(producto);
    
        Producto productoSave=productoRepository.save(producto);
        
        assertThat(productoSave).isNotNull();
        assertThat(productoSave.getStock()).isEqualTo(15);
    }

    @Test
    void EncontrarProducto(){
        
        Producto producto = Producto.builder()
        .nombre("Cerveza Aguila")
        .stock(2)
        .price(2000).build();
        productoRepository.save(producto);
        
        Optional<Producto> optionalProducto = productoRepository.findById(producto.getId());
        
        assertThat(optionalProducto).isPresent();
    }

    @Test
    void ActualizarProducto(){
        
        Producto producto = Producto.builder()
        .nombre("Cerveza Aguila")
        .stock(2)
        .price(2000).build();
        Producto productoGuardado = productoRepository.save(producto);
        
        productoGuardado.setNombre("Pericooo");
        productoRepository.save(productoGuardado);
        
        assertThat(productoGuardado.getNombre()).isEqualTo("Pericooo");
    }

    @Test
    void BorrarProducto(){
        
        Producto producto = Producto.builder()
        .nombre("Cerveza Aguila")
        .stock(2)
        .price(2000).build();
        Producto productoGuardado = productoRepository.save(producto);
        
        productoRepository.deleteById(productoGuardado.getId());
        
        assertThat(productoRepository.findById(productoGuardado.getId())).isEmpty();
    }

    @Test
    void RetoraUnaListProduct_EnBase_A_UnaPalabra(){
        
        Producto producto = Producto.builder()
        .nombre("Cerveza Aguila")
        .stock(2)
        .price(2000).build();
        productoRepository.save(producto);

        Producto producto2 = Producto.builder()
        .nombre("perico con limon")
        .stock(2)
        .price(2000).build();        
        productoRepository.save(producto2);
        
        Optional<List<Producto>> productosFound= productoRepository.findByNombreContainingIgnoreCase("limon");
        List<Producto> productos = productosFound.orElseThrow();
        assertThat(productos.size()).isEqualTo(1);
        assertThat(productos).isNotEmpty();

    }
    @Test
    void RetoraUnaListProduct_EnBase_A_UnaBusquedaDeStock(){
        
        Producto producto = Producto.builder()
        .nombre("Cerveza Aguila")
        .stock(12)
        .price(3000).build();
        productoRepository.save(producto);

        Producto producto2 = Producto.builder()
        .nombre("perico con limon")
        .stock(2)
        .price(2000).build();        
        productoRepository.save(producto2);
        
        
        Optional<List<Producto>> foundProducts= productoRepository.findByProductStockEqual(1);
        List<Producto> listPorductsFound =  foundProducts.get();
        
        assertThat(foundProducts).isPresent();
        assertThat(listPorductsFound).hasSize(2);


    }
    @Test
    void findProductsInDeterminatedStockAndPrice_WhenMatch_ThenReturnsAList(){
        
        Producto producto = Producto.builder()
        .nombre("Cerveza Aguila")
        .stock(16)
        .price(3000).build();
        productoRepository.save(producto);

        Producto producto2 = Producto.builder()
        .nombre("perico con limon")
        .stock(15)
        .price(2000).build();        
        productoRepository.save(producto2);

        
        Optional<List<Producto>> foundProducts= productoRepository.findBypriceAndStock(2000,16);
        
        assertThat(foundProducts).isPresent();
        List<Producto> productoList= foundProducts.get();
        assertThat(productoList).hasSize(1);

    }
}
