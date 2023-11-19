package proyecto.daw_1.proyecto.daw_1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import proyecto.daw_1.proyecto.daw_1.model.bd.Rol;
import proyecto.daw_1.proyecto.daw_1.repository.RolRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RolService {

    private RolRepository rolRepository;

    public List<Rol> listarRoles(){
        return rolRepository.findAll();
    }

    public Rol guardar(Rol tipousuario){
        return rolRepository.save(tipousuario);
    }

    public Optional<Rol> obtenerTipoUsuarioPorId(Integer id) {
        Optional<Rol> tipousuario = rolRepository.findById(id);
        return (tipousuario.isEmpty()) ? Optional.empty() : tipousuario;
    }
}
