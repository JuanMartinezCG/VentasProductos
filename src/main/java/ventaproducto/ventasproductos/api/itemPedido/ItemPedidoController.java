package ventaproducto.ventasproductos.api.itemPedido;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ventaproducto.ventasproductos.dto.ItemPedido.ItemPedidoDto;
import ventaproducto.ventasproductos.dto.ItemPedido.ItemPedidoDtoSave;
import ventaproducto.ventasproductos.servicies.ItemPedido.ItemPedidoInterface;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/v1/order-items")
public class ItemPedidoController {
    private ItemPedidoInterface itemPedidoService;

    public ItemPedidoController(ItemPedidoInterface itemPedidoService) {
        this.itemPedidoService = itemPedidoService;
    }

    //Crear un nuevo ItemPedido
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Integer> createDetalleEnvio(@RequestBody ItemPedidoDtoSave itemPedidoDtoSave) {
        itemPedidoService.guardarItemPedido(itemPedidoDtoSave);
        return ResponseEntity.ok().build();
    }

    // Obtener ItemPedido por ID
    @GetMapping("/{id}")
    public ResponseEntity<ItemPedidoDto> getDetalleEnvioById(@PathVariable("id") Long id) {
        ItemPedidoDto itemPedidoDto = itemPedidoService.findById(id);
        return ResponseEntity.ok(itemPedidoDto);
    }

    //Obtener todos los ItemsPedidos
    @GetMapping
    public ResponseEntity<Optional<List<ItemPedidoDto>>> getAllItemPedidos() {
        Optional<List<ItemPedidoDto>> itemsPedidos = itemPedidoService.getALLItemPedido();
        return ResponseEntity.ok(itemsPedidos);
    }

    //Obtener items pedidos por el ID del pedido
    @GetMapping("/order/{orderId}")
    public ResponseEntity<Optional<List<ItemPedidoDto>>> getDetalleEnvioByOrderId(@PathVariable("orderId") Long orderId){
        Optional<List<ItemPedidoDto>> itemsPedidos = itemPedidoService.findByPedidoId(orderId);
        return ResponseEntity.ok(itemsPedidos);
    }

    //Obtener DetalleEnvio por el ID del producto
    @GetMapping("/product/{productId}")
    public ResponseEntity<Optional<List<ItemPedidoDto>>> getDetalleEnvioByProductId(@PathVariable("orderId") Long productId){
        Optional<List<ItemPedidoDto>> itemsPedidos = itemPedidoService.findByProductoId(productId);
        return ResponseEntity.ok(itemsPedidos);
    }

    // Actualizar DetalleEnvio por ID
    @PutMapping("/{id}")
    public ResponseEntity<ItemPedidoDto> updateItemPedido(@RequestBody ItemPedidoDtoSave ItemPedidoDtoSaveJSON, @PathVariable("id") Long id) {
        ItemPedidoDto updatedItemPedido = itemPedidoService.actualizarItemPedido(id, ItemPedidoDtoSaveJSON);
        return ResponseEntity.ok(updatedItemPedido);
    }

    // Eliminar detalleEnvio por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItemPedido(@PathVariable("id") Long id) {
        itemPedidoService.EliminarItemPedido(id);
        return ResponseEntity.noContent().build();
    }

}
