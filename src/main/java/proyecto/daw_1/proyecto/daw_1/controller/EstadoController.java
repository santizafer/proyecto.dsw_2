package proyecto.daw_1.proyecto.daw_1.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import proyecto.daw_1.proyecto.daw_1.model.bd.Estado;
import proyecto.daw_1.proyecto.daw_1.service.EstadoService;

import java.util.Optional;

@AllArgsConstructor
@Controller
public class EstadoController {

    private EstadoService estadoService;

    /*

    @GetMapping("/listadoestados")
    public String listado_destinos(Model model) {
        model.addAttribute("listaestados", estadoService.listarEstados());
        return "listadoestados";
    }

    @GetMapping("/nuevoestado")
    public String registrar(Model model) {
        Estado estado = new Estado();
        model.addAttribute("estado", estado);
        return "nuevoestado";
    }

    @PostMapping("/guardarestado")
    public String guardarEstado(@ModelAttribute("estado") Estado estado) {
        estadoService.guardar(estado);
        return "nuevoestado";
    }

    @GetMapping("/actualizarestado/{id}")
    public String actualizar_estado(@PathVariable("id") Integer id, Model model) {
        Optional<Estado> estado = estadoService.obtenerEstadoPorId(id);
        model.addAttribute("estado", estado);
        return "/actualizarestado";
    }

    @GetMapping("/eliminarestado/{id}")
    public String eliminar_estado(@PathVariable("id") Integer id){
        estadoService.eliminarEstado(id);
        return "redirect:/listadoestados";
    }

     */


}
