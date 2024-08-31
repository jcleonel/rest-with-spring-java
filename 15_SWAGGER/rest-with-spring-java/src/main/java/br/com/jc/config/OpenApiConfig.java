package br.com.jc.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("RESTFul API")
                        .version("v1")
                        .description("Some Descrition")
                        .termsOfService("https://www.google.com/")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.google.com/")
                        )
                );
    }

}
