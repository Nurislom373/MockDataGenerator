package uz.jl.mockdata.mockdatagenerator;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@OpenAPIDefinition
@SpringBootApplication
@EnableTransactionManagement
public class MockDataGeneratorApplication {
    public static void main(String[] args) {
        SpringApplication.run(MockDataGeneratorApplication.class, args);
    }
}
