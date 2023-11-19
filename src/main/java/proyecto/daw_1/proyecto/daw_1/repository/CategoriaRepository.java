package proyecto.daw_1.proyecto.daw_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import proyecto.daw_1.proyecto.daw_1.model.bd.Categoria;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Integer> {
    @Query(value = "SELECT * FROM categoria WHERE nomcategoria LIKE %:filtro%", nativeQuery = true)
    List<Categoria> filtrarCategoriasPorNombreSQL(@Param("filtro") String filtro);
}
