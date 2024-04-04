package ventaproducto.ventasproductos.dto.Pedido;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import ventaproducto.ventasproductos.entities.Pedido;

@Mapper
public interface PedidoMapper {
    PedidoMapper INSTANCE = Mappers.getMapper(PedidoMapper.class);

    @Mapping(source = "cliente", target = "cliente")
    @Mapping(source = "itemPedidos", target = "itemPedidoDtos")
    @Mapping(source = "detalleEnvio", target = "detalleEnvioDto")
    @Mapping(source = "pago", target = "pagoDto")
    PedidoDto PedidoToPedidoDto(Pedido pedidoDto);
    @Mapping(source = "cliente", target = "cliente")
    Pedido DtoToPedido(PedidoDto pedido);
    @Mapping(source = "cliente", target = "cliente")
    Pedido Formato(PedidoDtoSave pedido);
}
