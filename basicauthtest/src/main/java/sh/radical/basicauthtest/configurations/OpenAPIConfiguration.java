package sh.radical.basicauthtest.configurations;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfiguration {

    @Bean
    public OpenAPI opeAPIConfiguration() {
        return new OpenAPI()
                .info(new Info().title("basicauthtest"))
                .components(
                        new Components()
                                .addSecuritySchemes(
                                        "Client Credentials Authentication Header",
                                        new SecurityScheme()
                                                .type(SecurityScheme.Type.HTTP)
                                                .in(SecurityScheme.In.HEADER)
                                                .bearerFormat("JWT")
                                                .scheme("bearer")
                                )
                )
                .addSecurityItem(
                        new SecurityRequirement()
                                .addList("Client Credentials Authentication Header")
                );
    }
}