package proyecto.daw_1.proyecto.daw_1.model.bd;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "usuario")
public class Usuario {
    @Id
    @Column(name="codusuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codusuario;

    @Column(name="nomusuario")
    private String nomusuario;

    @Column(name="apeusuario")
    private String apeusuario;

    @Column(name="emailusuario")
    private String emailusuario;

    @Column(name="teleusuario")
    private String teleusuario;

    @Column(name="passusuario")
    private String passusuario;

    @Column(name="estadousuario")
    private Boolean estadousuario;

    @ManyToOne()
    @JoinColumn(name = "idrol")
    private Rol rol;



}
