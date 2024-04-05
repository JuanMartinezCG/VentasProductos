package ventaproducto.ventasproductos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ventaproducto.ventasproductos.entities.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Long>{
    // buscar producto 
    Optional<List<Producto>> findByNombreContainingIgnoreCase(String termino);
    //buscar por Stock
    @Query("SELECT p FROM Producto p WHERE p.stock >= :stock")
    Optional<List<Producto>> findByProductStockEqual(Integer stock);
    //buscar por un precio y un stock
    @Query("SELECT p FROM Producto p WHERE p.price <= :price AND p.stock <= :stock")
    Optional<List<Producto>> findBypriceAndStock(int price, Integer stock);
}
