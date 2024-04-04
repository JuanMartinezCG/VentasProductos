package ventaproducto.ventasproductos.servicies.ItemPedido;

import java.util.List;
import java.util.Optional;

import ventaproducto.ventasproductos.dto.ItemPedido.ItemPedidoDto;
import ventaproducto.ventasproductos.dto.ItemPedido.ItemPedidoDtoSave;

public interface ItemPedidoInterface {

    ItemPedidoDto guardarItemPedido(ItemPedidoDtoSave itemPedido);

    ItemPedidoDto actualizarItemPedido(ItemPedidoDtoSave itemPedidoDto);

    Optional<List<ItemPedidoDto>> getALLItemPedido();

    ItemPedidoDto findById(Long id);

    void deleteById(Long id);

    Optional<List<ItemPedidoDto>> findByPedidoId(Long pedidoId);

    Optional<List<ItemPedidoDto>> findByProductoId(Long productoId);

    Optional<Integer> totalVentasPorProducto(Long ProductoId);
}
