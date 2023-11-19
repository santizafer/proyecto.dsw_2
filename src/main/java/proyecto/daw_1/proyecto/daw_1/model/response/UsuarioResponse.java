package proyecto.daw_1.proyecto.daw_1.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UsuarioResponse {
    private Integer codusuario;
    private String nomusuario;
    private String token;
}
