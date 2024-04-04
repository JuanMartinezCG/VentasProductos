package ventaproducto.ventasproductos.dto.Producto;

import java.math.BigDecimal;

public record ProductoDtoSave (
        Long id,
        String nombre,
        BigDecimal price,
        int stock){
    
}
