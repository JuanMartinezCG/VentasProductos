package ventaproducto.ventasproductos.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ventaproducto.ventasproductos.Enums.MetodoPago;
import ventaproducto.ventasproductos.entities.Pago;

@Repository
public interface PagoRepository extends JpaRepository<Pago,Long>{
    // buscar un pago entre las fechas seleccionadas
    Optional<List<Pago>> findByFechaPagoBetween(Date FechaInicio, Date FechaFin);
    // buscar un pago por medio de su IdPEdido y su metodo
    Optional<Pago> findByPedidoIdAndMetodoPago(Long pedidoId, MetodoPago metodoPago);
}
