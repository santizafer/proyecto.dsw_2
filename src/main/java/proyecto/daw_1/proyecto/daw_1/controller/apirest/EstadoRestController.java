package proyecto.daw_1.proyecto.daw_1.controller.apirest;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import proyecto.daw_1.proyecto.daw_1.exception.ResourceNotFoundException;
import proyecto.daw_1.proyecto.daw_1.model.bd.Estado;
import proyecto.daw_1.proyecto.daw_1.service.EstadoService;

import java.util.ArrayList;
import java.util.List;
@PreAuthorize("hasRole('ADMIN')")
@AllArgsConstructor
@RestController
@Controller
@RequestMapping(path = "api/v1/estado")
public class EstadoRestController {

    private EstadoService estadoService;

    @GetMapping("")
    public ResponseEntity<List<Estado>> listarEstados() {
        List<Estado> estadoList = new ArrayList<>();
        estadoService.listarEstados().forEach(estadoList::add);
        if(estadoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(estadoList, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estado> obtenerEstado(
            @PathVariable("id") Integer id){
        Estado estado = estadoService
                .obtenerEstadoPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("El estado con el Id Nro. "+
                        id + " no existe."));
        return new ResponseEntity<>(estado, HttpStatus.OK);
    }

    @GetMapping("/name/{filtro}")
    public ResponseEntity<List<Estado>> filtrarEstadosPorNombre(
            @PathVariable("filtro") String filtro){
        List<Estado> estadoList = new ArrayList<>();
        estadoService.obtenerEstadosPorFiltro(filtro)
                .forEach(estadoList::add);
        if(estadoList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(estadoList, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Estado> registrarEstado(@RequestBody Estado estado) {
        return new ResponseEntity<>(estadoService.guardar(estado), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estado> actualizarEstado(
            @PathVariable("id") Integer id,
            @RequestBody Estado estado
    ){
        Estado oldEstado = estadoService
                .obtenerEstadoPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("El estado con el Id Nro. "+
                        id + " no existe."));
        oldEstado.setNomestado(estado.getNomestado());
        return new ResponseEntity<>(
                estadoService.guardar(oldEstado), HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Estado> eliminarEstado(@PathVariable("id") Integer id){
        estadoService.eliminarEstado(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
