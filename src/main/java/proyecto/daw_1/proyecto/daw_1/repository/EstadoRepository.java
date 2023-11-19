package proyecto.daw_1.proyecto.daw_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import proyecto.daw_1.proyecto.daw_1.model.bd.Estado;

import java.util.List;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {

    @Query(value= "SELECT * FROM estado WHERE nomestado LIKE %:filtro%", nativeQuery = true)
    List<Estado> filtrarEstadosPorNombreSQL(@Param("filtro") String filtro);

}
