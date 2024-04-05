package ventaproducto.ventasproductos.servicies.ItemPedido;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ventaproducto.ventasproductos.dto.ItemPedido.ItemPedidoDto;
import ventaproducto.ventasproductos.dto.ItemPedido.ItemPedidoDtoSave;
import ventaproducto.ventasproductos.dto.ItemPedido.ItemPedidoMapper;
import ventaproducto.ventasproductos.entities.ItemPedido;
import ventaproducto.ventasproductos.repository.ItemPedidoRepository;

@Service
public class ItemPedidoServices implements ItemPedidoInterface{
    
    private final ItemPedidoRepository itemPedidoRepository;

    public ItemPedidoServices(ItemPedidoRepository itemPedidoRepository) {
        this.itemPedidoRepository = itemPedidoRepository;
    }
    
    @Override
    public ItemPedidoDto guardarItemPedido(ItemPedidoDtoSave itemPedidoJSon) {
        ItemPedido itemPedido= ItemPedidoMapper.INSTANCE.Formato(itemPedidoJSon);
        ItemPedido itemPedidoGuardado=itemPedidoRepository.save(itemPedido);
        return ItemPedidoMapper.INSTANCE.ItemPedidoToItemPedidoDto(itemPedidoGuardado);
    }

    @Override
    public ItemPedidoDto actualizarItemPedido(ItemPedidoDtoSave itemPedidoJSon) {
        ItemPedido itemPedido1= ItemPedidoMapper.INSTANCE.Formato(itemPedidoJSon);
        ItemPedido itemPedidoExiste=itemPedidoRepository.findById(itemPedidoJSon.id())
        .orElseThrow(() -> new RuntimeException(" ||| ItemPedido no encontrado con ID: ||| "));
        itemPedidoExiste.setProducto(itemPedido1.getProducto());
        itemPedidoExiste.setCantidad(itemPedido1.getCantidad());
        itemPedidoExiste.setPrecioUnitario(itemPedido1.getPrecioUnitario());
        itemPedidoExiste.setPedido(itemPedido1.getPedido());
        itemPedidoExiste=itemPedidoRepository.save(itemPedidoExiste);
        return ItemPedidoMapper.INSTANCE.ItemPedidoToItemPedidoDto(itemPedidoExiste);
    }

    @Override
    public Optional<List<ItemPedidoDto>> getALLItemPedido() {
        List<ItemPedido> itemPedido=itemPedidoRepository.findAll();
        List<ItemPedidoDto> itemPedidoDto=itemPedido.stream()
        .map(ItemPedidoMapper.INSTANCE::ItemPedidoToItemPedidoDto).collect(Collectors.toList());
        return Optional.of(itemPedidoDto);
    }

    @Override
    public ItemPedidoDto findById(Long id) {
        ItemPedido itemPedido=itemPedidoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException(" ||| ItemPedido no encontrado con ID: ||| "));
        return ItemPedidoMapper.INSTANCE.ItemPedidoToItemPedidoDto(itemPedido);
    }

    @Override
    public void EliminarItemPedido(Long id) {
        ItemPedido itemPedido=itemPedidoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException(" ||| ItemPedido no encontrado con ID: ||| "));
        itemPedidoRepository.delete(itemPedido);
    }

    @Override
    public Optional<List<ItemPedidoDto>> findByPedidoId(Long pedidoId) {
       List<ItemPedido> itemPedido= itemPedidoRepository.findByPedidoId(pedidoId)
        .orElseThrow(() -> new RuntimeException(" ||| ItemPedido no encontrado con ID: ||| "));
        List<ItemPedidoDto> itemPedidoDtos=itemPedido.stream().map(ItemPedidoMapper.INSTANCE::ItemPedidoToItemPedidoDto)
                .collect(Collectors.toList());
        return Optional.of(itemPedidoDtos);
    }

    @Override
    public Optional<List<ItemPedidoDto>> findByProductoId(Long productoId) {
        List<ItemPedido> itemPedidos= itemPedidoRepository.findByProductoId(productoId)
            .orElseThrow(() -> new RuntimeException(" ||| ItemPedido no encontrado con ID: ||| "));
        List<ItemPedidoDto> itemPedidoDtos=itemPedidos.stream().map(ItemPedidoMapper.INSTANCE::ItemPedidoToItemPedidoDto)
                .collect(Collectors.toList());
        return Optional.of(itemPedidoDtos);
    }

    @Override
    public Optional<Integer> totalVentasPorProducto(Long ProductoId) {
        return itemPedidoRepository.totalVentasPorProducto(ProductoId);
    }

}
