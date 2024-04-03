package ventaproducto.ventasproductos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ventaproducto.ventasproductos.entities.DetalleEnvio;


@Repository
public interface DetalleEnvioRepository extends JpaRepository<DetalleEnvio, Long>{
    // buscar por IdPedido
    List<DetalleEnvio> findByPedidoId(Long IdPedido);
    // buscar detalles de una transportadora 
    List<DetalleEnvio> findByTransportadora(String transportadora);
    
    // buscar detalles de un envio por su estado
    // List<DetalleEnvio> findByEstado(String estado);
}
