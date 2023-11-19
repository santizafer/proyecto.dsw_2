package proyecto.daw_1.proyecto.daw_1.model.bd;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@NoArgsConstructor
@Entity
@Data
@Table(name = "reserva")
public class Reserva {
    @Id
    @Column(name="codreserva")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codreserva;

    @Column(name="fecharegistro", nullable = false, updatable = false)
    @CreationTimestamp
    private String fecharegistro;

    @Column(name="fechareserva")
    private String fechareserva;

    @Column(name="horareserva")
    private String horareserva;

    @Column(name="numeropersonasreserva")
    private Integer numeropersonasreserva;

    @Column(name="infreserva")
    private String infreserva;

    @ManyToOne()
    @JoinColumn(name = "codusuario")
    private Usuario usuario;

    @ManyToOne()
    @JoinColumn(name = "coddestino")
    private Destino destino;

    @ManyToOne()
    @JoinColumn(name = "codestado")
    private Estado estado;

    @ManyToOne()
    @JoinColumn(name = "codpago")
    private MedioPago pago;

}
