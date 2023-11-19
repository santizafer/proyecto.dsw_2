package proyecto.daw_1.proyecto.daw_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import proyecto.daw_1.proyecto.daw_1.model.bd.MedioPago;

import java.util.List;

@Repository
public interface MedioPagoRepository extends JpaRepository<MedioPago, Integer> {

    @Query(value= "SELECT * FROM pago WHERE tipopago LIKE %:filtro%", nativeQuery = true)
    List<MedioPago> filtrarMedioPagoPorNombreSQL(@Param("filtro") String filtro);

}
