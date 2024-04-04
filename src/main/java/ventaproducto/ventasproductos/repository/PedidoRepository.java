package ventaproducto.ventasproductos.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ventaproducto.ventasproductos.Enums.StatusPedido;
import ventaproducto.ventasproductos.entities.Cliente;
import ventaproducto.ventasproductos.entities.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Long> {
    
    // buscar un pedido entre las fechas seleccionadas
    Optional<List<Pedido>> findByFechaPedidoBetween(LocalDateTime fechaInicio,LocalDateTime fechaFin);
    // buscar un pedido por un cliente y su estado de pedido
    @Query("SELECT p FROM Pedido p WHERE p.cliente.id = :clienteId AND p.estatusPedido = :estatusPedido")
    Optional<List<Pedido>> findByClienteAndstatusPedido(Long clienteId, StatusPedido estatusPedido);
    //Buscar Clientes con items pedidos
    @Query("SELECT p from Pedido p JOIN FETCH p.itemPedidos WHERE p.cliente = :cliente")
    Optional<List<Pedido>> findByClienteWhithItemPedidos(Cliente cliente);
}
