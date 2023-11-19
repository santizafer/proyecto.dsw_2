package proyecto.daw_1.proyecto.daw_1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import proyecto.daw_1.proyecto.daw_1.model.bd.Rol;
import proyecto.daw_1.proyecto.daw_1.model.bd.Usuario;
import proyecto.daw_1.proyecto.daw_1.repository.RolRepository;
import proyecto.daw_1.proyecto.daw_1.repository.UsuarioRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class UsuarioService {

    private UsuarioRepository usuarioRepository;
    private RolRepository rolRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder=
            new BCryptPasswordEncoder();

    public Usuario findUserByEmail(String email){
        return usuarioRepository.findByEmailusuario(email);
    }

    public Usuario findUserByUserName(String username){
        return usuarioRepository.findByNomusuario(username);
    }

    public List<Usuario> listarUsuarios(){
        return usuarioRepository.findAll();
    }

    public Usuario guardar(Usuario usuario){
        usuario.setPassusuario(bCryptPasswordEncoder.encode(
                usuario.getPassusuario()));
        usuario.setEstadousuario(true);
        Rol usuarioRol = rolRepository.findByNomrol("ADMIN");
        usuario.setRoles(new HashSet<>(Arrays.asList(usuarioRol)));
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> obtenerUsuarioPorId(Integer id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return (usuario.isEmpty()) ? Optional.empty() : usuario;
    }

    public void eliminarUsuario(Integer id){
        usuarioRepository.deleteById(id);
    }

    public String findUsuarioPorNombre(String nomusuario, String passusuario){
        Usuario obj = usuarioRepository.findByNomusuarioAndPassusuario(nomusuario, passusuario);
        return (obj!= null) ? "Inicio de sesión exitoso" : "Error al iniciar sesión";
    }
    public List<Usuario> obtenerUsuariosPorFiltro(String filtro){
        return usuarioRepository.filtrarUsuariosPorNombreSQL(filtro);
    }

    /*
    public List<Usuario> findByTipoUsuario(Integer id) {
        TipoUsuario tipousuario = tipoUsuarioRepository.findById(id).get();
        if (tipousuario != null) {
            List<Usuario> usuario = usuarioRepository.findByCodtipousuario(tipousuario);
            return usuario;
        } else {
            throw new InvalidDataAccessApiUsageException("Tipo de usuario no encontrada por el ID" + id);
        }
    }
    */


}
