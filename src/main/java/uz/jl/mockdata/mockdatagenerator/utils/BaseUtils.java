package uz.jl.mockdata.mockdatagenerator.utils;

import com.github.javafaker.Faker;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

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
}
