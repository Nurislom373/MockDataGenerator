package uz.jl.mockdata.mockdatagenerator.config;

import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author: Nurislom
 * <br/>
 * Date: 12/11/2022
 * <br/>
 * Time: 10:00 PM
 * <br/>
 * Package: uz.jl.mockdata.mockdatagenerator.config
 */
@Configuration
public class ApplicationConfig {

    @Bean
    public Faker faker() {
        return new Faker();
    }
}
