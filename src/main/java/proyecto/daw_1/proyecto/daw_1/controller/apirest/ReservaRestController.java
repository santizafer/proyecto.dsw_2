package proyecto.daw_1.proyecto.daw_1.controller.apirest;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import proyecto.daw_1.proyecto.daw_1.exception.ResourceNotFoundException;
import proyecto.daw_1.proyecto.daw_1.model.bd.Categoria;
import proyecto.daw_1.proyecto.daw_1.model.bd.Destino;
import proyecto.daw_1.proyecto.daw_1.model.bd.Reserva;
import proyecto.daw_1.proyecto.daw_1.model.bd.Usuario;
import proyecto.daw_1.proyecto.daw_1.model.dto.DestinoDto;
import proyecto.daw_1.proyecto.daw_1.model.dto.DtoEntity;
import proyecto.daw_1.proyecto.daw_1.model.dto.ReservaDto;
import proyecto.daw_1.proyecto.daw_1.service.ReservaService;
import proyecto.daw_1.proyecto.daw_1.util.DtoUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@Controller
@RequestMapping(path = "api/v1/reserva")
public class ReservaRestController {

    private ReservaService reservaService;

    @GetMapping("")
    public ResponseEntity<List<Reserva>> listarReservas() {
        List<Reserva> reservaList = new ArrayList<>();
        reservaService.listarReservas().forEach(reservaList::add);
        if(reservaList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(reservaList, HttpStatus.OK);
        }
    }

    @GetMapping("/dto")
    public ResponseEntity<List<DtoEntity>> listarReservasDto() {
        List<DtoEntity> reservaList = new ArrayList<>();
        reservaList = reservaService.listarReservas()
                .stream()
                .map(prod -> new DtoUtil().convertirADto(prod, new ReservaDto()))
                .collect(Collectors.toList());
        if(reservaList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(reservaList, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> obtenerReserva(
            @PathVariable("id") Integer id){
        Reserva reserva = reservaService
                .obtenerReservaPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("La reserva con el Id Nro. "+
                        id + " no existe."));
        return new ResponseEntity<>(reserva, HttpStatus.OK);
    }

    @GetMapping("/name/{filtro}")
    public ResponseEntity<List<Reserva>> filtrarReservasPorNombre(
            @PathVariable("filtro") String filtro){
        List<Reserva> reservaList = new ArrayList<>();
        reservaService.obtenerReservasPorFiltro(filtro)
                .forEach(reservaList::add);
        if(reservaList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(reservaList, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Reserva> registrarReserva(@RequestBody Reserva reserva) {
        return new ResponseEntity<>(reservaService.guardar(reserva), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> actualizarReserva(
            @PathVariable("id") Integer id,
            @RequestBody Reserva reserva
    ){
        Reserva oldReserva = reservaService
                .obtenerReservaPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("La reserva con el Id Nro. "+
                        id + " no existe."));
        oldReserva.setFecharegistro(reserva.getFecharegistro());
        oldReserva.setFechareserva(reserva.getFechareserva());
        oldReserva.setHorareserva(reserva.getHorareserva());
        oldReserva.setNumeropersonasreserva(reserva.getNumeropersonasreserva());
        oldReserva.setInfreserva(reserva.getInfreserva());
        oldReserva.setUsuario(reserva.getUsuario());
        oldReserva.setDestino(reserva.getDestino());
        oldReserva.setEstado(reserva.getEstado());
        oldReserva.setPago(reserva.getPago());

        return new ResponseEntity<>(
                reservaService.guardar(oldReserva), HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Reserva> eliminarReserva(@PathVariable("id") Integer id){
        reservaService.eliminarReserva(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
