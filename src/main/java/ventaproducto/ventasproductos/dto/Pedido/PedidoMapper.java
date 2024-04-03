package ventaproducto.ventasproductos.dto.Pedido;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import ventaproducto.ventasproductos.entities.Pedido;

@Mapper
public interface PedidoMapper {
    PedidoMapper INSTANCE = Mappers.getMapper(PedidoMapper.class);

    PedidoDto PedidoToPedidoDto(Pedido pedidoDto);
    Pedido DtoToPedido(PedidoDto pedido);
    Pedido Formato(PedidoDtoSave pedido);
}
