package proyecto.daw_1.proyecto.daw_1.util;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import proyecto.daw_1.proyecto.daw_1.model.dto.DtoEntity;
@Component
public class DtoUtil {
    public DtoEntity convertirADto(Object obj, DtoEntity mapper) {
        return new ModelMapper().map(obj, mapper.getClass());
    }

    public Object convertirAEntidad(Object obj, DtoEntity mapper) {
        return new ModelMapper().map(mapper, obj.getClass());
    }

}
