package proyecto.daw_1.proyecto.daw_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import proyecto.daw_1.proyecto.daw_1.model.bd.Usuario;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer > {
    Usuario findByNomusuarioAndPassusuario(String nomusuario, String passusuario);

    @Query(value = "SELECT * FROM usuario WHERE nomusuario LIKE %:filtro%", nativeQuery = true)
    List<Usuario> filtrarUsuariosPorNombreSQL(@Param("filtro") String filtro);

    /*List<Usuario> findByCodtipousuario(TipoUsuario codtipousuario);*/

    Usuario findByEmailusuario(String emailusuario);
    Usuario findByNomusuario(String nomusuario);

}
