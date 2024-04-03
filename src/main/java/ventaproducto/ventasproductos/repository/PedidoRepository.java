package ventaproducto.ventasproductos.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ventaproducto.ventasproductos.Enums.StatusPedido;
import ventaproducto.ventasproductos.entities.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Long> {
    
    // buscar un pedido entre las fechas seleccionadas
    List<Pedido> findByFechaPedidoBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);
    // buscar un pedido por un cliente y su estado de pedido
    List<Pedido> findByClienteAndStatusPedido(Long IdCliente, StatusPedido statusPedido);
}
