package proyecto.daw_1.proyecto.daw_1.service;

import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import proyecto.daw_1.proyecto.daw_1.model.bd.Rol;
import proyecto.daw_1.proyecto.daw_1.model.bd.Usuario;
import proyecto.daw_1.proyecto.daw_1.repository.UsuarioRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class DetalleUsuarioService implements UserDetailsService {

    private UsuarioRepository usuarioRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByNomusuario(username);
        return autenticacionUsuario(usuario,
                obtenerListaRolesUsuario(usuario.getRoles()));
    }
    public Usuario findByNomusuario(String usuario){
        return usuarioRepository.findByNomusuario(usuario);
    }
    public List<GrantedAuthority> obtenerListaRolesUsuario(Set<Rol> listaRoles){
        Set<GrantedAuthority> roles = new HashSet<>();
        for (Rol rol : listaRoles){
            roles.add(new SimpleGrantedAuthority(rol.getNomrol()));
        }
        return new ArrayList<GrantedAuthority>(roles);
    }
    private UserDetails autenticacionUsuario(Usuario usuario,
                                             List<GrantedAuthority> authorityList){

        // Comprobar si el usuario tiene roles asignados
        if (usuario.getRoles().isEmpty()) {
            throw new AccessDeniedException("El usuario no tiene roles asignados");
        }

        return new User(usuario.getNomusuario(),
                usuario.getPassusuario(),
                usuario.getEstadousuario(),
                true,
                true,
                true, authorityList);
    }



}