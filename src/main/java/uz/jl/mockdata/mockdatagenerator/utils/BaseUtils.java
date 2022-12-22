package uz.jl.mockdata.mockdatagenerator.utils;

import com.github.javafaker.Faker;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import uz.jl.mockdata.mockdatagenerator.data.dto.DataCreateDTO;

import java.io.File;
import java.io.IOException;

@Getter
@Setter
@Component
public class BaseUtils {
    // Objs
    public static final Faker FAKER = new Faker();
    // Paths
    public static final String API = "/api";
    public static final String VERSION = "/v1";
    public static final String PATH = API + VERSION;

    public static void checkData(DataCreateDTO dto) {
        if (dto.getRowCount() < 0 || dto.getRowCount() > 1e7) {
            throw new RuntimeException("Invalid Row Count!");
        }
    }
}
