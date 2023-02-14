package uz.jl.mockdata.mockdatagenerator.data;

import uz.jl.mockdata.mockdatagenerator.data.dto.DataCreateDTO;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

public interface DataService {

    UUID generate(DataCreateDTO dto);

    void get(HttpServletResponse response, UUID id);

    void delete();
}
