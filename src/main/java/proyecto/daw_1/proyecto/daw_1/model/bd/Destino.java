package proyecto.daw_1.proyecto.daw_1.model.bd;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@Data
@Entity
@Table(name = "destino")
public class Destino {
    @Id
    @Column(name="coddestino")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer coddestino;

    @Column(name="nomdestino")
    private String nomdestino;

    @Column(name="desdestino")
    private String desdestino;

    @Column(name="predestino")
    private double predestino;

    @ManyToOne()
    @JoinColumn(name = "codcategoria")
    private Categoria categoria;
    
}
