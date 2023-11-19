package proyecto.daw_1.proyecto.daw_1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import proyecto.daw_1.proyecto.daw_1.model.bd.Destino;
import proyecto.daw_1.proyecto.daw_1.model.bd.Usuario;
import proyecto.daw_1.proyecto.daw_1.repository.DestinoRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DestinoService {

    private DestinoRepository destinoRepository;

    public List<Destino> listarDestinos(){
        return destinoRepository.findAll();
    }

    public Destino guardar(Destino destino){
        return destinoRepository.save(destino);
    }

    public Optional<Destino> obtenerDestinoPorId(Integer id) {
        Optional<Destino> destino = destinoRepository.findById(id);
        return (destino.isEmpty()) ? Optional.empty() : destino;
    }

    public void eliminarDestino(Integer id){
        destinoRepository.deleteById(id);
    }

    public List<Destino> obtenerDestinosPorFiltro(String filtro){
        return destinoRepository.filtrarDestinosPorNombreSQL(filtro);
    }


}
