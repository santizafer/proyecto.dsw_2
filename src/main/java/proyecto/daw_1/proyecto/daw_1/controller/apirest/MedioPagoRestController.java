package proyecto.daw_1.proyecto.daw_1.controller.apirest;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import proyecto.daw_1.proyecto.daw_1.exception.ResourceNotFoundException;
import proyecto.daw_1.proyecto.daw_1.model.bd.MedioPago;
import proyecto.daw_1.proyecto.daw_1.service.MedioPagoService;

import java.util.ArrayList;
import java.util.List;

@PreAuthorize("hasRole('ADMIN')")
@AllArgsConstructor
@RestController
@Controller
@RequestMapping(path = "api/v1/pago")
public class MedioPagoRestController {

    private MedioPagoService medioPagoService;

    @GetMapping("")
    public ResponseEntity<List<MedioPago>> listarMedioPagos() {
        List<MedioPago> mediopagoList = new ArrayList<>();
        medioPagoService.listarMedioPago().forEach(mediopagoList::add);
        if(mediopagoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(mediopagoList, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedioPago> obtenerMedioPago(
            @PathVariable("id") Integer id){
        MedioPago mediopago = medioPagoService
                .obtenerMedioPagoPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("El medio de pago con el Id Nro. "+
                        id + " no existe."));
        return new ResponseEntity<>(mediopago, HttpStatus.OK);
    }

    @GetMapping("/name/{filtro}")
    public ResponseEntity<List<MedioPago>> filtrarMedioPagosPorNombre(
            @PathVariable("filtro") String filtro){
        List<MedioPago> mediopagoList = new ArrayList<>();
        medioPagoService.obtenerMedioPagoPorFiltro(filtro)
                .forEach(mediopagoList::add);
        if(mediopagoList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(mediopagoList, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<MedioPago> registrarMedioPago(@RequestBody MedioPago mediopago) {
        return new ResponseEntity<>(medioPagoService.guardar(mediopago), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedioPago> actualizarMedioPago(
            @PathVariable("id") Integer id,
            @RequestBody MedioPago mediopago
    ){
        MedioPago oldMediopago = medioPagoService
                .obtenerMedioPagoPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("El medio de pago con el Id Nro. "+
                        id + " no existe."));
        oldMediopago.setTipopago(mediopago.getTipopago());
        return new ResponseEntity<>(
                medioPagoService.guardar(oldMediopago), HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MedioPago> eliminarMedioPago(@PathVariable("id") Integer id){
        medioPagoService.eliminarMedioPago(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
