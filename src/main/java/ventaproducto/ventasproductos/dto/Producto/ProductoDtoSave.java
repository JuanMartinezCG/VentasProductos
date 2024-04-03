package ventaproducto.ventasproductos.dto.Producto;

import java.math.BigDecimal;

public record ProductoDtoSave (
    String nombre,
    BigDecimal pric,
    int stock
) {
    
}
