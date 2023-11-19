package proyecto.daw_1.proyecto.daw_1.controller.estaticos;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import proyecto.daw_1.proyecto.daw_1.model.bd.Estado;
import proyecto.daw_1.proyecto.daw_1.service.EstadoService;

@AllArgsConstructor
@Controller
public class EstaticosController {

    private EstadoService estadoService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("activepage", "index");
        model.addAttribute("listaestados", estadoService.listarEstados());
        return "index";
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

    @GetMapping("/gastronomia")
    public String gastronomia(Model model) {
        return "destinos/gastronomia";
    }

    @GetMapping("/galeria")
    public String galeria(Model model) {
        return "destinos/galeria";
    }

    @GetMapping("/videos")
    public String videos(Model model) {
        return "destinos/videos";
    }

    @GetMapping("/quienes-somos")
    public String quienes_somos(Model model) {
        return "destinos/quienes-somos";
    }

    @GetMapping("/contactenos")
    public String contactenos(Model model) {
        return "destinos/contactenos";
    }

    @GetMapping("/atractivos-turisticos")
    public String atractivos_turisticos(Model model) {
        return "destinos/atractivos-turisticos";
    }

    @GetMapping("/destinos/fortaleza-real-felipe")
    public String fortaleza_real_felipe(Model model) {
        return "destinos/fortaleza-real-felipe";
    }

    @GetMapping("/destinos/museo-naval-peru")
    public String museo_naval_peru(Model model) {
        return "destinos/museo-naval-peru";
    }

    @GetMapping("/destinos/zona-monumental-callao")
    public String zona_monumental_callao(Model model) {
        return "destinos/zona-monumental-callao";
    }

    @GetMapping("/destinos/huaca-el-paraiso")
    public String huaca_el_paraiso(Model model) {
        return "destinos/huaca-el-paraiso";
    }

    @GetMapping("/destinos/museo-submarino-abtao")
    public String museo_submarino_abtao(Model model) {
        return "destinos/museo-submarino-abtao";
    }

    @GetMapping("/destinos/humedales-de-ventanilla")
    public String humedales_de_ventanilla(Model model) {
        return "destinos/humedales-de-ventanilla";
    }

    @GetMapping("/destinos/plaza-matriz")
    public String plaza_matriz(Model model) {
        return "destinos/plaza-matriz";
    }

    @GetMapping("/destinos/isla-el-fronton")
    public String isla_el_fronton(Model model) {
        return "destinos/isla-el-fronton";
    }

    @GetMapping("/destinos/islas-cavinzas")
    public String islas_cavinzas(Model model) {
        return "destinos/islas-cavinzas";
    }

    @GetMapping("/destinos/balneario-chucuito")
    public String balneario_chucuito(Model model) {
        return "destinos/balneario-chucuito";
    }

}
