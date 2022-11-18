package uz.jl.mockdata.mockdatagenerator.data;

import org.springframework.core.io.Resource;
import uz.jl.mockdata.mockdatagenerator.data.dto.DataCreateDTO;

import java.util.UUID;

public interface DataService {
    UUID generate(DataCreateDTO dto);

    Resource get(UUID id);

    void delete();
}
