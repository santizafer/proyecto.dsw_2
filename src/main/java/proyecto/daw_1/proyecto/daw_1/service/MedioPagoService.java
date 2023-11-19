package proyecto.daw_1.proyecto.daw_1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import proyecto.daw_1.proyecto.daw_1.model.bd.MedioPago;
import proyecto.daw_1.proyecto.daw_1.repository.MedioPagoRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MedioPagoService {

    private MedioPagoRepository medioPagoRepository;

    public List<MedioPago> listarMedioPago(){
        return medioPagoRepository.findAll();
    }

    public MedioPago guardar(MedioPago mediopago){
        return medioPagoRepository.save(mediopago);
    }

    public Optional<MedioPago> obtenerMedioPagoPorId(Integer id){
        Optional<MedioPago> mediopago = medioPagoRepository.findById(id);
        return (mediopago.isEmpty()) ? Optional.empty() : mediopago;
    }

    public void eliminarMedioPago(Integer id) { medioPagoRepository.deleteById(id); }

    public List<MedioPago> obtenerMedioPagoPorFiltro(String filtro) {
        return medioPagoRepository.filtrarMedioPagoPorNombreSQL(filtro);
    }
}
