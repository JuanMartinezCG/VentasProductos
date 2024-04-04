package ventaproducto.ventasproductos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ventaproducto.ventasproductos.entities.ItemPedido;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
    // buscar un item por IdPedido
    Optional<List<ItemPedido>> findByPedidoId(Long idPedido);
    // buscar un item por IdProducto
    Optional<List<ItemPedido>> findByProductoId(Long idProducto);
    //
    @Query("SELECT SUM(ip.cantidad*ip.precioUnitario) FROM ItemPedido ip WHERE ip.producto.id= :productoId")
    Optional<Integer> totalVentasPorProducto(@Param("productoId")Long productoId);
}
