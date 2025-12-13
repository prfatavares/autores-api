package br.com.atavares.autoresapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Autores API",
                version = "v1",
                contact = @Contact(
                        name = "Alexandre Tavares",
                        email = "alexandre@atavares.com.br",
                        url = "http://www.atavares.com.br"
                )
        ),
        security = {
                @SecurityRequirement(name = "basic-auth")
        }
)
@SecurityScheme(
        name = "basic-auth",
        type = SecuritySchemeType.HTTP,
        scheme = "basic"
)
public class OpenApiConfiguration {
}
