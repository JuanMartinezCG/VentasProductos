package ventaproducto.ventasproductos.Repository;

import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ventaproducto.ventasproductos.AbstractIntegrationDBTest;
import ventaproducto.ventasproductos.Enums.MetodoPago;
import ventaproducto.ventasproductos.Enums.StatusPedido;
import ventaproducto.ventasproductos.entities.Cliente;
import ventaproducto.ventasproductos.entities.Pago;
import ventaproducto.ventasproductos.entities.Pedido;
import ventaproducto.ventasproductos.repository.ClienteRepository;
import ventaproducto.ventasproductos.repository.PagoRepository;
import ventaproducto.ventasproductos.repository.PedidoRepository;

public class PagoRepositoryTest extends AbstractIntegrationDBTest{
    
    PagoRepository pagoRepository;
    PedidoRepository pedidoRepository;
    ClienteRepository clienteRepository;
    Cliente customer;
    Pedido pedido;
    Pago pago;
    
    @Autowired
    public PagoRepositoryTest(PagoRepository pagoRepository, PedidoRepository pedidoRepository, ClienteRepository clienteRepository) {
        this.pagoRepository = pagoRepository;
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
    }

    @BeforeEach
    void setUp() {
        pagoRepository.deleteAll();
        pedidoRepository.deleteAll();
        clienteRepository.deleteAll();
    }

    @Test
    void CrearPago(){
        
        customer = Cliente.builder()
            .nombre("juan")
            .email("tumama@gmail.com")
            .direccion("cra 26 C 48")
            .build();
        clienteRepository.save(customer);

        pedido = Pedido.builder()
            .fechaPedido(LocalDateTime.of(2024, Month.AUGUST,10,8,20))
            .estatusPedido(StatusPedido.ENVIADO)
            .cliente(customer)
            .build();
        pedidoRepository.save(pedido);

        pago = Pago.builder()
            .totalPago(123F)
            .fechaPago(LocalDateTime.of(2024,Month.JULY,20,15,10))
            .metodoPago(MetodoPago.EFECTIVO)
            .pedido(pedido).build();
        Pago pagoGuardado = pagoRepository.save(pago);
        
        assertThat(pagoGuardado).isNotNull();
        assertThat(pagoRepository.findById(pagoGuardado.getId())).isPresent();
    }

    @Test
    void BuscaPago_Por_ID(){
        
        customer = Cliente.builder()
            .nombre("juan")
            .email("tumama@gmail.com")
            .direccion("cra 26 C 48")
            .build();
        clienteRepository.save(customer);

        pedido = Pedido.builder()
            .fechaPedido(LocalDateTime.of(2024, Month.AUGUST,10,8,20))
            .estatusPedido(StatusPedido.ENVIADO)
            .cliente(customer)
            .build();
        pedidoRepository.save(pedido);

        pago = Pago.builder()
            .totalPago(123F)
            .fechaPago(LocalDateTime.of(2024,Month.JULY,20,15,10))
            .metodoPago(MetodoPago.EFECTIVO)
            .pedido(pedido).build();
        Pago pagoGuardado=pagoRepository.save(pago);
        
        Optional<Pago> optionalPago=pagoRepository.findById(pagoGuardado.getId());
        
        assertThat(optionalPago).isPresent();
    }

    @Test
    void ActualizarPago(){
        
        customer = Cliente.builder()
            .nombre("juan")
            .email("tumama@gmail.com")
            .direccion("cra 26 C 48")
            .build();
        clienteRepository.save(customer);

        pedido = Pedido.builder()
            .fechaPedido(LocalDateTime.of(2024, Month.AUGUST,10,8,20))
            .estatusPedido(StatusPedido.ENVIADO)
            .cliente(customer)
            .build();
        pedidoRepository.save(pedido);

        pago = Pago.builder()
            .totalPago(123F)
            .fechaPago(LocalDateTime.of(2024,Month.JULY,20,15,10))
            .metodoPago(MetodoPago.EFECTIVO)
            .pedido(pedido).build();
        Pago pagoGuardado=pagoRepository.save(pago);
        
        //se esta actualizando la Fecha
        pagoGuardado.setFechaPago(LocalDateTime.of(2024,Month.JULY,25,15,10));
        pagoRepository.save(pagoGuardado);
        
        assertThat(pagoGuardado.getFechaPago()).isEqualTo(LocalDateTime.of(2024,Month.JULY,25,15,10));
    }

    @Test
    void eliminarPago(){
        
        customer = Cliente.builder()
            .nombre("juan")
            .email("tumama@gmail.com")
            .direccion("cra 26 C 48")
            .build();
        clienteRepository.save(customer);

        pedido = Pedido.builder()
            .fechaPedido(LocalDateTime.of(2024, Month.AUGUST,10,8,20))
            .estatusPedido(StatusPedido.ENVIADO)
            .cliente(customer)
            .build();
        pedidoRepository.save(pedido);

        pago = Pago.builder()
            .totalPago(123F)
            .fechaPago(LocalDateTime.of(2024,Month.JULY,20,15,10))
            .metodoPago(MetodoPago.EFECTIVO)
            .pedido(pedido).build();
        Pago pagoGuardado = pagoRepository.save(pago);
        
        pagoRepository.deleteById(pagoGuardado.getId());
        
        assertThat(pagoRepository.findById(pagoGuardado.getId())).isEmpty();
    }
    @Test
    void RetornaListaEntreFechas(){
        customer = Cliente.builder()
            .nombre("juan")
            .email("tumama@gmail.com")
            .direccion("cra 26 C 48")
            .build();
        clienteRepository.save(customer);

        pedido = Pedido.builder()
            .fechaPedido(LocalDateTime.of(2024, Month.AUGUST,10,8,20))
            .estatusPedido(StatusPedido.ENVIADO)
            .cliente(customer)
            .build();
        pedidoRepository.save(pedido);

        Pedido pedido2 = Pedido.builder()
            .fechaPedido(LocalDateTime.of(2024, Month.AUGUST,10,8,20))
            .estatusPedido(StatusPedido.ENVIADO)
            .cliente(customer)
            .build();
        pedidoRepository.save(pedido2);

        Pago pago= Pago.builder().fechaPago(LocalDateTime.of(2024,Month.JULY,10,4,30))
                .metodoPago(MetodoPago.EFECTIVO)
                .totalPago(12F)
                .pedido(pedido).build();
        pagoRepository.save(pago);

        Pago pago2= Pago.builder()
                .fechaPago(LocalDateTime.of(2024,Month.DECEMBER,23,10,39))
                .metodoPago(MetodoPago.NEQUI)
                .totalPago(12F)
                .pedido(pedido2).build();
        pagoRepository.save(pago2);
        

        Optional<List<Pago>> pagosGuardados =pagoRepository.findByFechaPagoBetween(LocalDateTime.of(2024,Month.JULY,5,1,1)
                ,LocalDateTime.of(2025,Month.JULY,13,4,30));
        
        assertThat(pagosGuardados).isPresent();
        assertThat(pagosGuardados.get()).hasSize(2);
    }
    @Test
    void BuscaPorId_Y_MeTodoDePago(){
        
        customer = Cliente.builder()
            .nombre("juan")
            .email("tumama@gmail.com")
            .direccion("cra 26 C 48")
            .build();
        clienteRepository.save(customer);

        pedido = Pedido.builder()
            .fechaPedido(LocalDateTime.of(2024, Month.AUGUST,10,8,20))
            .estatusPedido(StatusPedido.ENVIADO)
            .cliente(customer)
            .build();
        pedidoRepository.save(pedido);

        pago = Pago.builder()
            .totalPago(123F)
            .fechaPago(LocalDateTime.of(2024,Month.JULY,20,15,10))
            .metodoPago(MetodoPago.EFECTIVO)
            .pedido(pedido).build();
        pagoRepository.save(pago);
       
        Optional<Pago> pagoGuarado = pagoRepository.findByPedidoIdAndMetodoPago(pago.getId(), MetodoPago.EFECTIVO);
        
        assertThat(pagoGuarado).isPresent();
        assertThat(pagoGuarado.get().getMetodoPago()).isEqualTo(MetodoPago.EFECTIVO);
    }
}
