package ventaproducto.ventasproductos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import ventaproducto.ventasproductos.entities.DetalleEnvio;


@Repository
public interface DetalleEnvioRepository extends JpaRepository<DetalleEnvio, Long>{
    // buscar por IdPedido
    Optional<DetalleEnvio> findByPedidoId(Long IdPedido);
    // buscar detalles de una transportadora 
    Optional<List<DetalleEnvio>> findByTransportadora(String transportadora);
    // buscar detalles de un envio por su estado
}
