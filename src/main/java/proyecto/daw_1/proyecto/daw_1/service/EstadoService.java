package proyecto.daw_1.proyecto.daw_1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import proyecto.daw_1.proyecto.daw_1.model.bd.Estado;
import proyecto.daw_1.proyecto.daw_1.repository.EstadoRepository;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EstadoService {

    private EstadoRepository estadoRepository;

    public List<Estado> listarEstados(){
        return estadoRepository.findAll();
    }

    public Estado guardar(Estado estado){
        return estadoRepository.save(estado);
    }

    public Optional<Estado> obtenerEstadoPorId(Integer id){
        Optional<Estado> estado = estadoRepository.findById(id);
        return (estado.isEmpty()) ? Optional.empty() : estado;
    }

    public void eliminarEstado(Integer id) { estadoRepository.deleteById(id); }

    public List<Estado> obtenerEstadosPorFiltro(String filtro) {
        return estadoRepository.filtrarEstadosPorNombreSQL(filtro);
    }

}
