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

    @Column(name="nombresusuario")
    private String nombresusuario;

    @Column(name="apellidosusuario")
    private String apellidosusuario;

    @Column(name="emailusuario")
    private String emailusuario;

    @Column(name="teleusuario")
    private String teleusuario;

    @Column(name="passusuario")
    private String passusuario;

    @Column(name="estadousuario")
    private Boolean estadousuario;

    @ManyToMany(
            cascade = CascadeType.MERGE,
            fetch = FetchType.EAGER )
    @JoinTable(name = "usuario_rol", joinColumns =
    @JoinColumn(name = "codusuario"),
            inverseJoinColumns = @JoinColumn(name = "idrol"))
    private Set<Rol> roles;



}
