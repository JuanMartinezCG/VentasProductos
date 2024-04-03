package ventaproducto.ventasproductos.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ventaproducto.ventasproductos.entities.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Long>{
    // buscar producto 
    List<Producto> findByNombre(String nombre);
    //buscar por Stock
    List<Producto> findByStockGreaterThan(int stock);
    //buscar por un precio y un stock
    List<Producto> findByPriceLessThanEqualAndStockLessThanEqual(BigDecimal price, int stock);
}
