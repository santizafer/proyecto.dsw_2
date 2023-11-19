package proyecto.daw_1.proyecto.daw_1.controller.apirest;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import proyecto.daw_1.proyecto.daw_1.exception.ResourceNotFoundException;
import proyecto.daw_1.proyecto.daw_1.model.bd.Destino;
import proyecto.daw_1.proyecto.daw_1.model.dto.DestinoDto;
import proyecto.daw_1.proyecto.daw_1.model.dto.DtoEntity;
import proyecto.daw_1.proyecto.daw_1.service.DestinoService;
import proyecto.daw_1.proyecto.daw_1.util.DtoUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@PreAuthorize("hasRole('ADMIN')")
@AllArgsConstructor
@RestController
@Controller
@RequestMapping(path = "api/v1/destino")

public class DestinoRestController {
    
    private DestinoService destinoService;

    @GetMapping("")
    public ResponseEntity<List<Destino>> listarDestinos() {
        List<Destino> destinoList = new ArrayList<>();
        destinoService.listarDestinos().forEach(destinoList::add);
        if(destinoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(destinoList, HttpStatus.OK);
        }
    }

    @GetMapping("/dto")
    public ResponseEntity<List<DtoEntity>> listarDestinosDto() {
        List<DtoEntity> reservaList = new ArrayList<>();
        reservaList = destinoService.listarDestinos()
                .stream()
                .map(prod -> new DtoUtil().convertirADto(prod, new DestinoDto()))
                .collect(Collectors.toList());
        if(reservaList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(reservaList, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Destino> obtenerDestino(
            @PathVariable("id") Integer id){
        Destino destino = destinoService
                .obtenerDestinoPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("El destino con el Id Nro. "+
                        id + " no existe."));
        return new ResponseEntity<>(destino, HttpStatus.OK);
    }

    @GetMapping("/name/{filtro}")
    public ResponseEntity<List<Destino>> filtrarDestinosPorNombre(
            @PathVariable("filtro") String filtro){
        List<Destino> destinoList = new ArrayList<>();
        destinoService.obtenerDestinosPorFiltro(filtro)
                .forEach(destinoList::add);
        if(destinoList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(destinoList, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Destino> registrarDestino(@RequestBody Destino destino) {
        return new ResponseEntity<>(destinoService.guardar(destino), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Destino> actualizarDestino(
            @PathVariable("id") Integer id,
            @RequestBody Destino destino
    ){
        Destino oldDestino = destinoService
                .obtenerDestinoPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("El destino con el Id Nro. "+
                        id + " no existe."));
        oldDestino.setNomdestino(destino.getNomdestino());
        oldDestino.setDesdestino(destino.getDesdestino());
        oldDestino.setPredestino(destino.getPredestino());
        oldDestino.setCategoria(destino.getCategoria());

        return new ResponseEntity<>(
                destinoService.guardar(oldDestino), HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Destino> eliminarDestino(@PathVariable("id") Integer id){
        destinoService.eliminarDestino(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
