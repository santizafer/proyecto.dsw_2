package proyecto.daw_1.proyecto.daw_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import proyecto.daw_1.proyecto.daw_1.model.bd.Reserva;

import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {

    @Query(value = "SELECT * FROM reserva WHERE infreserva LIKE %:filtro%", nativeQuery = true)
    List<Reserva> filtrarReservasPorNombreSQL(@Param("filtro") String filtro);

}
