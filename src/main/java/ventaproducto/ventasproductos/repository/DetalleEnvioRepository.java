package ventaproducto.ventasproductos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ventaproducto.ventasproductos.Enums.StatusPedido;
import ventaproducto.ventasproductos.entities.DetalleEnvio;


@Repository
public interface DetalleEnvioRepository extends JpaRepository<DetalleEnvio, Long>{
    // buscar por IdPedido
    Optional<DetalleEnvio> findByPedidoId(Long IdPedido);
    // buscar detalles de una transportadora 
    Optional<List<DetalleEnvio>> findByTransportadora(String transportadora);
    // Buscar los detalles de envio por estado
    @Query("SELECT de FROM DetalleEnvio de INNER JOIN de.pedido p WHERE p.statusPedido= :estado")
    Optional<List<DetalleEnvio>> findByestado(@Param("estado")StatusPedido estado);
}
