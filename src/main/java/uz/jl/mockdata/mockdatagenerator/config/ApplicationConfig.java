package uz.jl.mockdata.mockdatagenerator.config;

import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public Faker faker() {
        return new Faker();
    }
}
