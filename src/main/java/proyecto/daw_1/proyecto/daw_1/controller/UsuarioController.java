package proyecto.daw_1.proyecto.daw_1.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import proyecto.daw_1.proyecto.daw_1.model.bd.Usuario;
import proyecto.daw_1.proyecto.daw_1.service.RolService;
import proyecto.daw_1.proyecto.daw_1.service.UsuarioService;
import java.util.Optional;


@AllArgsConstructor
@Controller
public class UsuarioController {

    private UsuarioService usuarioService;
    private RolService rolService;

    @GetMapping("/login")
    public String login(Model model) {
        Usuario usuario = new Usuario();
        model.addAttribute("usuario", usuario);
        model.addAttribute("errormensaje", false);
        return "login";
    }

    @GetMapping("/registrar")
    public String registrar2(Model model){
        Usuario usuario = new Usuario();
        model.addAttribute("usuario", usuario);
        model.addAttribute("listarol", rolService.listarRoles());
        return "registrar";
    }

    @GetMapping("/listadousuarios")
    public String listado_usuarios(Model model) {
        model.addAttribute("listausuarios", usuarioService.listarUsuarios());
        return "listadousuarios";
    }

    @GetMapping("/nuevousuario")
    public String registrar(Model model) {
        Usuario usuario = new Usuario();
        model.addAttribute("usuario", usuario);
        model.addAttribute("listarol", rolService.listarRoles());
        return "nuevousuario";
    }

    @PostMapping("/guardarusuario")
    public String guardarUsuario(@ModelAttribute("usuario") Usuario usuario) {
        usuarioService.guardar(usuario);
        return "login";
    }

    @PostMapping("/login")
    public String validacionLogin (
            @RequestParam("nomusuario") String username,
            @RequestParam("passusuario") String password,
            Model model
    ) {
        String mensaje = usuarioService.findUsuarioPorNombre(username, password);
        if(mensaje.equals("Inicio de sesión exitoso")) {
            return "redirect:/admin";
        } else {
            model.addAttribute("errormensaje", true);
            model.addAttribute("mensaje", "usuario o contraseña incorrecta");
            return "redirect:/login";
        }
    }

    @GetMapping("/actualizarusuario/{id}")
    public String actualizar_usuario(@PathVariable("id") Integer id, Model model) {
        Optional<Usuario> usuario = usuarioService.obtenerUsuarioPorId(id);
        model.addAttribute("usuario", usuario);
        model.addAttribute("listarol", rolService.listarRoles());
        return "/actualizarusuario";
    }

    @GetMapping("/eliminarusuario/{id}")
    public String eliminar_usuario(@PathVariable("id") Integer id){
        usuarioService.eliminarUsuario(id);
        return "redirect:/listadousuarios";
    }


}
