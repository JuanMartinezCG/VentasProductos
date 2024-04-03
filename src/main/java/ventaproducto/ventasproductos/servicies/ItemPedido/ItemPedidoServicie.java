package ventaproducto.ventasproductos.servicies.ItemPedido;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ventaproducto.ventasproductos.dto.ItemPedido.ItemPedidoDto;
import ventaproducto.ventasproductos.dto.ItemPedido.ItemPedidoDtoSave;
import ventaproducto.ventasproductos.dto.ItemPedido.ItemPedidoMapper;
import ventaproducto.ventasproductos.entities.ItemPedido;
import ventaproducto.ventasproductos.repository.ItemPedidoRepository;

@Transactional
@Service
public class ItemPedidoServicie implements ItemPedidoInterface {
    
    private final ItemPedidoRepository  itemPedidoRepository;

    public ItemPedidoServicie(ItemPedidoRepository itemPedidoRepository) {
        this.itemPedidoRepository = itemPedidoRepository;
    }

    @Override
    public ItemPedidoDto guardarItemPedido(ItemPedidoDtoSave ItemPedidoJSON) {
        ItemPedido itemPedido = ItemPedidoMapper.INSTANCE.Formato(ItemPedidoJSON);
        ItemPedido itemPedidoGuardado = itemPedidoRepository.save(itemPedido);
        return ItemPedidoMapper.INSTANCE.ItemPedidoToItemPedidoDto(itemPedidoGuardado);
    }

    @Override
    public ItemPedidoDto actualizarItemPedido(ItemPedidoDtoSave ItemPedidoJSON) {

        ItemPedido itemPedido =  ItemPedidoMapper.INSTANCE.Formato(ItemPedidoJSON);
        ItemPedido itemPedidoExiste = itemPedidoRepository.findById(ItemPedidoJSON.id())
        .orElseThrow(()-> new RuntimeException(" ||| No Se Encontro El Item |||"));

        itemPedidoExiste.setProducto(itemPedido.getProducto());
        itemPedidoExiste.setCantidad(itemPedido.getCantidad());
        itemPedidoExiste.setPrecioUnitario(itemPedido.getPrecioUnitario());
        itemPedidoExiste.setPedido(itemPedido.getPedido());

        itemPedidoExiste = itemPedidoRepository.save(itemPedidoExiste);

        return ItemPedidoMapper.INSTANCE.ItemPedidoToItemPedidoDto(itemPedidoExiste);
    }

    @Override
    public Optional<List<ItemPedidoDto>> getIAlltemPedido() {
        List<ItemPedido> itemPedidoExistente = itemPedidoRepository.findAll();
        List<ItemPedidoDto> itemPedidoDto = itemPedidoExistente.stream().map(ItemPedidoMapper.INSTANCE::ItemPedidoToItemPedidoDto).collect(Collectors.toList());
        return Optional.of(itemPedidoDto);
    }

    @Override
    public ItemPedidoDto findById(Long id) {
        ItemPedido itemPedidoExistente = itemPedidoRepository.findById(id)
        .orElseThrow(()-> new RuntimeException("||| No Se Encontro El Item |||"));
        return ItemPedidoMapper.INSTANCE.ItemPedidoToItemPedidoDto(itemPedidoExistente);
    }


    @Override
    public void EliminarItemPedido(Long id) {
        ItemPedido itemPedidoExistente = itemPedidoRepository.findById(id)
        .orElseThrow(()-> new RuntimeException("||| No Se Encontro El Item |||"));
        itemPedidoRepository.delete(itemPedidoExistente);
    }

    @Override
    public Optional<List<ItemPedidoDto>> findByPedidoId(Long pedidoId) {
       List<ItemPedido> itemPedidoExistente = itemPedidoRepository.findByPedidoId(pedidoId)
            .orElseThrow(()-> new RuntimeException("||| No se encontró el pedido |||"));
        List<ItemPedidoDto> itemPedidoDtos = itemPedidoExistente.stream().map(ItemPedidoMapper.INSTANCE::ItemPedidoToItemPedidoDto)
            .collect(Collectors.toList());
        return Optional.of(itemPedidoDtos);
    }

    @Override
    public Optional<List<ItemPedidoDto>> findByProductoId(Long productoId) {
        List<ItemPedido> itemPedidoExistente = itemPedidoRepository.findByProductoId(productoId)
            .orElseThrow(()->new RuntimeException("||| No se encontró el pedido |||"));
        List<ItemPedidoDto> itemPedidoDtos = itemPedidoExistente.stream().map(ItemPedidoMapper.INSTANCE::ItemPedidoToItemPedidoDto)
            .collect(Collectors.toList());
        return Optional.of(itemPedidoDtos);
    }
}
