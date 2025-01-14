package ventaproducto.ventasproductos.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ventaproducto.ventasproductos.Enums.MetodoPago;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Pagos")
@Builder
public class Pago {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name="pedidoId")
    private Pedido pedido;

    @Column
    private BigDecimal totalPago;
    
    @Column
    private LocalDateTime fechaPago;
    
    @Enumerated(EnumType.STRING)
    @Column
    private MetodoPago metodoPago;

}
