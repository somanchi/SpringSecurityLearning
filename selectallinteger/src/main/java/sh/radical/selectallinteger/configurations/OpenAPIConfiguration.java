package sh.radical.selectallinteger.configurations;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public  class OpenAPIConfiguration  {



    @Bean
    public    OpenAPI opeAPIConfiguration()
    {
        return new OpenAPI()
                .info(new Info().title("authtest"))
                .components(new Components()
                        .addSecuritySchemes("Basic Authentication Header", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .in(SecurityScheme.In.HEADER)
                                .scheme("Basic")
                                .bearerFormat("Basic ")
                        ))
                .addSecurityItem(new SecurityRequirement().addList("Basic Authentication Header"));
    }

}