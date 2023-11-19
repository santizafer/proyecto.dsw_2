package proyecto.daw_1.proyecto.daw_1.model.bd;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@Data
@Entity
@Table(name = "pago")
public class MedioPago {
    @Id
    @Column(name="codpago")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codpago;

    @Column(name="tipopago")
    private String tipopago;

}
