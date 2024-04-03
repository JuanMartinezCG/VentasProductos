package ventaproducto.ventasproductos.servicies.ItemPedido;

import java.util.List;
import java.util.Optional;

import ventaproducto.ventasproductos.dto.ItemPedido.ItemPedidoDto;
import ventaproducto.ventasproductos.dto.ItemPedido.ItemPedidoDtoSave;

public interface ItemPedidoInterface {

    ItemPedidoDto guardarItemPedido(ItemPedidoDtoSave itemPedido);

    ItemPedidoDto actualizarItemPedido(ItemPedidoDtoSave itemPedido);
    
    Optional<List<ItemPedidoDto>> getIAlltemPedido();

    ItemPedidoDto findById(Long id);
    
    void EliminarItemPedido(Long id);

    Optional<List<ItemPedidoDto>> findByPedidoId(Long pedidoId);

    Optional<List<ItemPedidoDto>> findByProductoId(Long productoId);    
}
