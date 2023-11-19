package proyecto.daw_1.proyecto.daw_1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import proyecto.daw_1.proyecto.daw_1.model.bd.Reserva;
import proyecto.daw_1.proyecto.daw_1.repository.ReservaRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReservaService {

    private ReservaRepository reservaRepository;

    public List<Reserva> listarReservas(){
        return reservaRepository.findAll();
    }

    public Reserva guardar(Reserva reserva){
        return reservaRepository.save(reserva);
    }

    public Optional<Reserva> obtenerReservaPorId(Integer id) {
        Optional<Reserva> reserva = reservaRepository.findById(id);
        return (reserva.isEmpty()) ? Optional.empty() : reserva;
    }

    public void eliminarReserva(Integer id){
        reservaRepository.deleteById(id);
    }

    public List<Reserva> obtenerReservasPorFiltro(String filtro){
        return reservaRepository.filtrarReservasPorNombreSQL(filtro);
    }

}
