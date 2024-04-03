package ventaproducto.ventasproductos.dto.Cliente;


import org.mapstruct.Mapper;

import org.mapstruct.factory.Mappers;

import ventaproducto.ventasproductos.entities.Cliente;

@Mapper
public interface ClienteMapper {

    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    ClienteDto clienteToClienteDto(Cliente clienteDto);
    Cliente DtoToCliente(ClienteDto cliente);
    Cliente Formato(ClienteDtoSave cliente);


    
}

