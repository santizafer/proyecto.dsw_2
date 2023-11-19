package proyecto.daw_1.proyecto.daw_1.model.bd;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "categoria")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codcategoria;

    @Column(name="nomcategoria")
    private String nomcategoria;

    @Column(name="descategoria")
    private String descategoria;

}
