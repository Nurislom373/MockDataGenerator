package uz.jl.mockdata.mockdatagenerator.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Author: Nurislom
 * <br/>
 * Date: 12/11/2022
 * <br/>
 * Time: 11:00 PM
 * <br/>
 * Package: uz.jl.mockdata.mockdatagenerator.config
 */
@Configuration
@EnableWebMvc
public class RestApiServletConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        super.addCorsMappings(registry);
        registry.addMapping("/**")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedOrigins("*")
                .allowedHeaders("*");
    }
}
