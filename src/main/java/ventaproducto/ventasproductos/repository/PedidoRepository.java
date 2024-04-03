package ventaproducto.ventasproductos.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ventaproducto.ventasproductos.Enums.StatusPedido;
import ventaproducto.ventasproductos.entities.Cliente;
import ventaproducto.ventasproductos.entities.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Long> {
    
    // buscar un pedido entre las fechas seleccionadas
    List<Pedido> findByFechaPedidoBetween(Date fechaInicio, Date fechaFin);
    // buscar un pedido por un cliente y su estado de pedido
    List<Pedido> findByClienteAndStatusPedido(Cliente cliente, StatusPedido statusPedido);
}
