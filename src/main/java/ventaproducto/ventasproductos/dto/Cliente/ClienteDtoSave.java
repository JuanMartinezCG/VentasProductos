package ventaproducto.ventasproductos.dto.Cliente;

public record ClienteDtoSave(
        Long id,
        String nombre, 
        String email, 
        String direccion
        ) {

}
