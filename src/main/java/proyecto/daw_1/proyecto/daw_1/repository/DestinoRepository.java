package proyecto.daw_1.proyecto.daw_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import proyecto.daw_1.proyecto.daw_1.model.bd.Destino;

import java.util.List;

@Repository
public interface DestinoRepository extends JpaRepository<Destino, Integer> {

    @Query(value = "SELECT * FROM destino WHERE nomdestino LIKE %:filtro%", nativeQuery = true)
    List<Destino> filtrarDestinosPorNombreSQL(@Param("filtro") String filtro);

}
