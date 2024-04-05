package ventaproducto.ventasproductos.api.detalleEnvio;

import org.springframework.web.bind.annotation.RestController;

import ventaproducto.ventasproductos.dto.DetalleEnvio.DetalleEnvioDto;
import ventaproducto.ventasproductos.dto.DetalleEnvio.DetalleEnvioDtoSave;
import ventaproducto.ventasproductos.servicies.DetalleEnvio.DetalleEnvioInterface;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/v1/shipping")
public class DetalleEnvioController {
   
    private DetalleEnvioInterface detalleEnvioService;

    public DetalleEnvioController(DetalleEnvioInterface detalleEnvio) {
        this.detalleEnvioService = detalleEnvio;
    }

    //Crear un nuevo DetalleEnvio
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Integer> createDetalleEnvio(@RequestBody DetalleEnvioDtoSave detalleEnvioDtoSaveJSON) {
        detalleEnvioService.guardarDetalleEnvio(detalleEnvioDtoSaveJSON);
        return ResponseEntity.ok().build();
    }

    // Obtener DetalleEnvio por ID
    @GetMapping("/{id}")
    public ResponseEntity<DetalleEnvioDto> getDetalleEnvioById(@PathVariable("id") Long id) {
        DetalleEnvioDto detalleEnvioDto = detalleEnvioService.findById(id);
        return ResponseEntity.ok(detalleEnvioDto);
    }

    //Obtener todos los DetalleEnvios
    @GetMapping
    public ResponseEntity<Optional<List<DetalleEnvioDto>>> getAllDetalleEnvios() {
        Optional<List<DetalleEnvioDto>> detalleEnvioDtos = detalleEnvioService.getAllDestalleEnvio();
        return ResponseEntity.ok(detalleEnvioDtos);
    }

    //Obtener DetalleEnvio por el ID del pedido
    @GetMapping("/order/{orderId}")
    public ResponseEntity<Optional<DetalleEnvioDto>> getDetalleEnvioByOrderId(@PathVariable("orderId") Long orderId){
        Optional<DetalleEnvioDto> detalleEnvio = detalleEnvioService.findByPedidoId(orderId);
        return ResponseEntity.ok(detalleEnvio);
    }

    //Obtener DetalleEnvio por el transportadora
    @GetMapping("/carrier?name=")
    public ResponseEntity<Optional<List<DetalleEnvioDto>>> getDetalleEnvioByCarrier(@RequestParam("carrier") String carrierName){
        Optional<List<DetalleEnvioDto>> detallesEnvios = detalleEnvioService.findByTransportadora(carrierName);
        return ResponseEntity.ok(detallesEnvios);
    }

    // Actualizar DetalleEnvio por ID
    @PutMapping("/{id}")
    public ResponseEntity<DetalleEnvioDto> updateDetalleEnvio(@PathVariable("id") Long id, @RequestBody DetalleEnvioDtoSave detalleEnvioDtoSaveJSON) {
        DetalleEnvioDto updatedDetalleEnvio = detalleEnvioService.actualizarDetalleEnvio(id, detalleEnvioDtoSaveJSON);
        return ResponseEntity.ok(updatedDetalleEnvio);
    }

    // Eliminar detalleEnvio por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetalleEnvio(@PathVariable("id") Long id) {
        detalleEnvioService.EliminarDetalleEnvio(id);
        return ResponseEntity.noContent().build();
    }
    
}