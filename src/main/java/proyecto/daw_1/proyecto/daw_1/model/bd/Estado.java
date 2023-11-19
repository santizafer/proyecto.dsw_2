package proyecto.daw_1.proyecto.daw_1.model.bd;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@Data
@Entity
@Table(name = "estado")
public class Estado {
    @Id
    @Column(name="codestado")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codestado;

    @Column(name="nomestado")
    private String nomestado;

}
