package ventaproducto.ventasproductos.api.pedido;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ventaproducto.ventasproductos.Enums.StatusPedido;
import ventaproducto.ventasproductos.dto.Pedido.PedidoDto;
import ventaproducto.ventasproductos.dto.Pedido.PedidoDtoSave;
import ventaproducto.ventasproductos.servicies.Pedido.PedidoInterface;

@RestController
@RequestMapping("/api/v1/orders")
public class PedidoController {
    private PedidoInterface pedidoService;

    public PedidoController(PedidoInterface pedidoService) {
        this.pedidoService = pedidoService;
    }

    //Crear un nuevo Pedido
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Integer> createPedido(@RequestBody PedidoDtoSave pedidoDtoSaveJson) {
        pedidoService.guardarPedido(pedidoDtoSaveJson);
        return ResponseEntity.ok().build();
    }

    // Obtener Pedido por ID
    @GetMapping("/{id}")
    public ResponseEntity<PedidoDto> getPedidoById(@PathVariable("id") Long id) {
        PedidoDto PedidoDto = pedidoService.findById(id);
        return ResponseEntity.ok(PedidoDto);
    }

    //Obtener todos los ItemsPedidos
    @GetMapping
    public ResponseEntity<Optional<List<PedidoDto>>> getAllPedidos() {
        Optional<List<PedidoDto>> pedidoDto = pedidoService.getAllPedido();
        return ResponseEntity.ok(pedidoDto);
    }

    //Obtener pedidos entre dos fechas
    @GetMapping("/date-range?startDate=&endDate=")
    public ResponseEntity<Optional<List<PedidoDto>>> getPedidoBetween(@RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate){
        Optional<List<PedidoDto>> pedidosDto = pedidoService.findByFechaPedidoBetween(startDate, endDate);
        return ResponseEntity.ok(pedidosDto);
    }

    //Obtener DetalleEnvio por el ID del producto
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<Optional<List<PedidoDto>>> getDetalleEnvioByCustomerId(@PathVariable("customerId") Long customerId, @RequestParam StatusPedido statusPedido){
        Optional<List<PedidoDto>> pedidos = pedidoService.findByClienteAndEstado(customerId, null);
        return ResponseEntity.ok(pedidos);
    }

    // Actualizar DetalleEnvio por ID
    @PutMapping("/{id}")
    public ResponseEntity<PedidoDto> updatePedido(@RequestBody PedidoDtoSave PedidoDtoSaveJSON, @PathVariable("id") Long id) {
        PedidoDto pedido = pedidoService.actualizarPedido(id, PedidoDtoSaveJSON);
        return ResponseEntity.ok(pedido);
    }

    // Eliminar detalleEnvio por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePEdido(@PathVariable("id") Long id) {
        pedidoService.EliminarPeido(id);
        return ResponseEntity.noContent().build();
    }
}
