package proyecto.daw_1.proyecto.daw_1.controller.apirest;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import proyecto.daw_1.proyecto.daw_1.exception.ResourceNotFoundException;
import proyecto.daw_1.proyecto.daw_1.model.bd.Categoria;
import proyecto.daw_1.proyecto.daw_1.model.dto.CategoriaDto;
import proyecto.daw_1.proyecto.daw_1.model.dto.DtoEntity;
import proyecto.daw_1.proyecto.daw_1.service.CategoriaService;
import proyecto.daw_1.proyecto.daw_1.util.DtoUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@PreAuthorize("hasRole('ADMIN')")
@AllArgsConstructor
@RestController
@Controller
@RequestMapping(path = "api/v1/categoria")
public class CategoriaRestController {

    private CategoriaService categoriaService;

    @GetMapping("")
    public ResponseEntity<List<Categoria>> listarCategorias() {
        List<Categoria> categoriaList = new ArrayList<>();
        categoriaService.listarCategorias().forEach(categoriaList::add);
        if(categoriaList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(categoriaList, HttpStatus.OK);
        }
    }

    @GetMapping("/dto")
    public ResponseEntity<List<DtoEntity>> listarCategoriasDto() {
        List<DtoEntity> categoriaList = new ArrayList<>();
        categoriaList = categoriaService.listarCategorias()
                .stream()
                .map(prod -> new DtoUtil().convertirADto(prod, new CategoriaDto()))
                .collect(Collectors.toList());
        if(categoriaList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(categoriaList, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> obtenerCategoria(
            @PathVariable("id") Integer id){
        Categoria categoria = categoriaService
                .obtenerCategoriaPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("La categoría con el Id Nro. "+
                        id + " no existe."));
        return new ResponseEntity<>(categoria, HttpStatus.OK);
    }

    @GetMapping("/name/{filtro}")
    public ResponseEntity<List<Categoria>> filtrarCategoriasPorNombre(
            @PathVariable("filtro") String filtro){
        List<Categoria> categoriaList = new ArrayList<>();
        categoriaService.obtenerCategoriasPorFiltro(filtro)
                .forEach(categoriaList::add);
        if(categoriaList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categoriaList, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Categoria> registrarCategoria(@RequestBody Categoria categoria) {
        return new ResponseEntity<>(categoriaService.guardar(categoria), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> actualizarCategoria(
            @PathVariable("id") Integer id,
            @RequestBody Categoria categoria
    ){
        Categoria oldCategoria = categoriaService
                .obtenerCategoriaPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("La categoría con el Id Nro. "+
                        id + " no existe."));
        oldCategoria.setNomcategoria(categoria.getNomcategoria());
        oldCategoria.setDescategoria(categoria.getDescategoria());
        return new ResponseEntity<>(
                categoriaService.guardar(oldCategoria), HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Categoria> eliminarCategoria(@PathVariable("id") Integer id){
        categoriaService.eliminarCategoria(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
