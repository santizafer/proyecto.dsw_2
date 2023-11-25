package proyecto.daw_1.proyecto.daw_1.model.dto;

import lombok.Data;

@Data
public class ReservaDto implements DtoEntity {
    private Integer codreserva;
    private String fecharegistro;
    private UsuarioDto usuario;
    private DestinoDto destino;
    private EstadoDto estado;
    private MedioPagoDto pago;
}
