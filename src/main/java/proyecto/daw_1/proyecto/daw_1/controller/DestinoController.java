package proyecto.daw_1.proyecto.daw_1.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import proyecto.daw_1.proyecto.daw_1.model.bd.Destino;
import proyecto.daw_1.proyecto.daw_1.service.DestinoService;

import java.util.Optional;

@AllArgsConstructor
@Controller
public class DestinoController {

    private DestinoService destinoService;

    @GetMapping("/listadodestinos")
    public String listado_destinos(Model model) {
        model.addAttribute("listadestinos", destinoService.listarDestinos());
        return "listadodestinos";
    }
    @GetMapping("/nuevodestino")
    public String registrar(Model model) {
        Destino destino = new Destino();
        model.addAttribute("destino", destino);
        return "nuevodestino";
    }
    @PostMapping("/guardardestino")
    public String guardarDestino(@ModelAttribute("destino") Destino destino) {
        destinoService.guardar(destino);
        return "nuevodestino";
    }
    @GetMapping("/actualizardestino/{id}")
    public String actualizar_destino(@PathVariable("id") Integer id, Model model) {
        Optional<Destino> destino = destinoService.obtenerDestinoPorId(id);
        model.addAttribute("destino", destino);
        return "/actualizardestino";
    }
    @GetMapping("/eliminardestino/{id}")
    public String eliminar_destino(@PathVariable("id") Integer id){
        destinoService.eliminarDestino(id);
        return "redirect:/listadodestinos";
    }
}
