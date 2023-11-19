package proyecto.daw_1.proyecto.daw_1.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import proyecto.daw_1.proyecto.daw_1.model.bd.Reserva;
import proyecto.daw_1.proyecto.daw_1.service.*;

import java.util.Optional;

@AllArgsConstructor
@Controller
public class ReservaController {

    private ReservaService reservaService;
    private UsuarioService usuarioService;
    private DestinoService destinoService;
    private EstadoService estadoService;
    private MedioPagoService medioPagoService;

    @GetMapping("/listadoreservas")
    public String listado_destinos(Model model) {
        model.addAttribute("listareservas", reservaService.listarReservas());
        return "listadoreservas";
    }

    @GetMapping("/nuevareserva")
    public String registrar(Model model) {
        Reserva reserva = new Reserva();
        model.addAttribute("reserva", reserva);
        model.addAttribute("listausuarios", usuarioService.listarUsuarios());
        model.addAttribute("listadestinos", destinoService.listarDestinos());
        model.addAttribute("listaestados", estadoService.listarEstados());
        model.addAttribute("listamediopago", medioPagoService.listarMedioPago());
        return "nuevareserva";
    }

    @PostMapping("/guardarreserva")
    public String guardarReserva(@ModelAttribute("reserva") Reserva reserva) {
        reservaService.guardar(reserva);
        return "nuevareserva";
    }

    @GetMapping("/actualizarreserva/{id}")
    public String actualizar_reserva(@PathVariable("id") Integer id, Model model) {
        Optional<Reserva> reserva = reservaService.obtenerReservaPorId(id);
        model.addAttribute("reserva", reserva);
        model.addAttribute("listausuarios", usuarioService.listarUsuarios());
        model.addAttribute("listadestinos", destinoService.listarDestinos());
        model.addAttribute("listaestados", estadoService.listarEstados());
        model.addAttribute("listamediopago", medioPagoService.listarMedioPago());
        return "/actualizarreserva";
    }

    @GetMapping("/eliminarreserva/{id}")
    public String eliminar_reserva(@PathVariable("id") Integer id){
        reservaService.eliminarReserva(id);
        return "redirect:/listadoreservas";
    }    
    
}
