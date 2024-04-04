package ventaproducto.ventasproductos.entities;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ventaproducto.ventasproductos.Enums.StatusPedido;


@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Pedidos")
@Builder
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "clienteid")
    private Cliente cliente;
    
    @Column
    private LocalDateTime fechaPedido;

    @Column
    @Enumerated(EnumType.STRING)
    private StatusPedido estatusPedido;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itemPedidos;

    @OneToOne(mappedBy = "pedido")
    private DetalleEnvio detalleEnvio;

    @OneToOne(fetch = FetchType.EAGER,mappedBy = "pedido")
    private Pago pago;
}
