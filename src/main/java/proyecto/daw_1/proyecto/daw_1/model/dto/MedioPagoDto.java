package proyecto.daw_1.proyecto.daw_1.model.dto;

import lombok.Data;

@Data
public class MedioPagoDto implements DtoEntity{
    private Integer codpago;
    private String tipopago;
}
