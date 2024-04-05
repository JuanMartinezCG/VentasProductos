package ventaproducto.ventasproductos.api.pago;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ventaproducto.ventasproductos.Enums.MetodoPago;
import ventaproducto.ventasproductos.dto.Pago.PagoDto;
import ventaproducto.ventasproductos.dto.Pago.PagoDtoSave;
import ventaproducto.ventasproductos.servicies.pago.PagoInterface;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("api/v1/payments")
public class PagoController {
    private PagoInterface pagoService;

    public PagoController(PagoInterface pagoService) {
        this.pagoService = pagoService;
    }

    //Crear un nuevo pago
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Integer> createPago(@RequestBody PagoDtoSave pagoDtoSave) {
        pagoService.guardarPago(pagoDtoSave);
        return ResponseEntity.ok().build();
    }

    // Obtener pagos por ID
    @GetMapping("/{id}")
    public ResponseEntity<PagoDto> getPagoById(@PathVariable("id") Long id) {
        PagoDto pagoDto = pagoService.findById(id);
        return ResponseEntity.ok(pagoDto);
    }

    //Obtener todos los pagos
    @GetMapping
    public ResponseEntity<Optional<List<PagoDto>>> getAllPagos() {
        Optional<List<PagoDto>> pagos = pagoService.getAllPago();
        return ResponseEntity.ok(pagos);
    }

    //Obtener items pedidos por el ID del pedido
    @GetMapping("/order/{orderId}")
    public ResponseEntity<Optional<PagoDto>> getPagoByOrderId(@PathVariable("orderId") Long orderId, @RequestParam MetodoPago metodoPago){
        Optional<PagoDto> pagoDto = pagoService.findByIdAndMetodoDePago(orderId, metodoPago);
        return ResponseEntity.ok(pagoDto);
    }

    @GetMapping("/date-range?startDate=&endDate=")
    public ResponseEntity<Optional<List<PagoDto>>> getPagosBetween(@RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate){
        Optional<List<PagoDto>> pagosDto = pagoService.findByFechaPagoBetween(startDate, endDate);
        return ResponseEntity.ok(pagosDto);
    }
    
    //Actualizar Pago por id
    @PutMapping("/{id}")
    public ResponseEntity<PagoDto> updatePago(@RequestBody PagoDtoSave PagoDtoSaveJSON, @PathVariable("id") Long id) {
        PagoDto updatedPago = pagoService.actualizarPago(id, PagoDtoSaveJSON);
        return ResponseEntity.ok(updatedPago);
    }

    // Eliminar pago por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePago(@PathVariable("id") Long id) {
        pagoService.eliminarPAgo(id);
        return ResponseEntity.noContent().build();
    }

}
