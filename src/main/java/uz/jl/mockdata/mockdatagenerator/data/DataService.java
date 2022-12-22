package uz.jl.mockdata.mockdatagenerator.data;

import uz.jl.mockdata.mockdatagenerator.data.dto.DataCreateDTO;

import java.io.File;
import java.util.UUID;

public interface DataService {

    UUID generate(DataCreateDTO dto);

    File get(UUID id);

    void delete();
}
