package proyecto.daw_1.proyecto.daw_1.controller.apirest;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import proyecto.daw_1.proyecto.daw_1.exception.ResourceNotFoundException;
import proyecto.daw_1.proyecto.daw_1.model.bd.Usuario;
import proyecto.daw_1.proyecto.daw_1.model.dto.DtoEntity;
import proyecto.daw_1.proyecto.daw_1.model.dto.UsuarioDto;
import proyecto.daw_1.proyecto.daw_1.repository.RolRepository;
import proyecto.daw_1.proyecto.daw_1.service.UsuarioService;
import proyecto.daw_1.proyecto.daw_1.util.DtoUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@Controller
@RequestMapping(path = "api/v1/usuario")
public class UsuarioRestController {

    private UsuarioService usuarioService;

    private RolRepository tipoUsuarioRepository;

    @GetMapping("")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarioList = new ArrayList<>();
        usuarioService.listarUsuarios().forEach(usuarioList::add);
        if(usuarioList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(usuarioList, HttpStatus.OK);
        }
    }

    @GetMapping("/dto")
    public ResponseEntity<List<DtoEntity>> listarUsuariosDto() {
        List<DtoEntity> reservaList = new ArrayList<>();
        reservaList = usuarioService.listarUsuarios()
                .stream()
                .map(prod -> new DtoUtil().convertirADto(prod, new UsuarioDto()))
                .collect(Collectors.toList());
        if(reservaList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(reservaList, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerCategoria(
            @PathVariable("id") Integer id){
        Usuario usuario = usuarioService
                .obtenerUsuarioPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("El usuario con el Id Nro. "+
                        id + " no existe."));
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @GetMapping("/name/{filtro}")
    public ResponseEntity<List<Usuario>> filtrarUsuariosPorNombre(
            @PathVariable("filtro") String filtro){
        List<Usuario> usuarioList = new ArrayList<>();
        usuarioService.obtenerUsuariosPorFiltro(filtro)
                .forEach(usuarioList::add);
        if(usuarioList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(usuarioList, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Usuario> registrarUsuario(@RequestBody Usuario usuario) {
        return new ResponseEntity<>(usuarioService.guardar(usuario), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(
            @PathVariable("id") Integer id,
            @RequestBody Usuario usuario
    ){
        Usuario oldUsuario = usuarioService
                .obtenerUsuarioPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("El usuario con el Id Nro. "+
                        id + " no existe."));
        oldUsuario.setNomusuario(usuario.getNomusuario());
        oldUsuario.setApeusuario(usuario.getApeusuario());
        oldUsuario.setEmailusuario(usuario.getEmailusuario());
        oldUsuario.setTeleusuario(usuario.getTeleusuario());
        oldUsuario.setPassusuario(usuario.getPassusuario());
        oldUsuario.setEstadousuario(usuario.getEstadousuario());
        oldUsuario.setRol(usuario.getRol());
        return new ResponseEntity<>(
                usuarioService.guardar(oldUsuario), HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> eliminarUsuario(@PathVariable("id") Integer id){
        usuarioService.eliminarUsuario(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
