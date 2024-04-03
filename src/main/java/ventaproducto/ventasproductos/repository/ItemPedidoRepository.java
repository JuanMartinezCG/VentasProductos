package ventaproducto.ventasproductos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ventaproducto.ventasproductos.entities.ItemPedido;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
    // buscar un item por IdPedido
    List<ItemPedido> findByPedidoId(Long idPedido);
    // buscar un item por IdProducto
    List<ItemPedido> findByProductoId(Long idProducto);

    // @Query("SELECT SUM(ip.cantidad * ip.precio) FROM ItemPedido ip WHERE ip.producto.id = :productoId")
    // Double calcularTotalVentasPorProducto(Long productoId);
}
