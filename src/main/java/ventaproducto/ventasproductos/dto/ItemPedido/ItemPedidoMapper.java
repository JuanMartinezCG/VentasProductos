package ventaproducto.ventasproductos.dto.ItemPedido;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import ventaproducto.ventasproductos.entities.ItemPedido;

@Mapper
public interface ItemPedidoMapper {
    ItemPedidoMapper INSTANCE = Mappers.getMapper(ItemPedidoMapper.class);

    ItemPedidoDto ItemPedidoToItemPedidoDto(ItemPedido itemPedidoDto);
    ItemPedido DtoToItemPedido(ItemPedidoDto itemPedido);
    ItemPedido Formato(ItemPedidoDtoSave itemPedido);
}
