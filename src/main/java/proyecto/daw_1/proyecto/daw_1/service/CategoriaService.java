package proyecto.daw_1.proyecto.daw_1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import proyecto.daw_1.proyecto.daw_1.model.bd.Categoria;
import proyecto.daw_1.proyecto.daw_1.repository.CategoriaRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoriaService {
    private CategoriaRepository categoriaRepository;

    public List<Categoria> listarCategorias(){
        return categoriaRepository.findAll();
    }

    public Categoria guardar(Categoria categoria){
        return categoriaRepository.save(categoria);
    }

    public Optional<Categoria> obtenerCategoriaPorId(Integer id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        return (categoria.isEmpty()) ? Optional.empty() : categoria;
    }

    public void eliminarCategoria(Integer id){
        categoriaRepository.deleteById(id);
    }

    public List<Categoria> obtenerCategoriasPorFiltro(String filtro){
        return categoriaRepository.filtrarCategoriasPorNombreSQL(filtro);
    }
}
