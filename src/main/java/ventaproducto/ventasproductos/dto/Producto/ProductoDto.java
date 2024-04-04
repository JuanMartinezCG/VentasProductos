package ventaproducto.ventasproductos.dto.Producto;

import java.math.BigDecimal;

public record ProductoDto(
            Long id,
            String nombre,
            BigDecimal price,
            int stock){

    
}
