package com.onlinetaskmanagementsystem.otms.config;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
@OpenAPIDefinition(
        info = @Info (
                contact = @Contact(
                        name = "otms",
                        email = "ronaldhino@gmail.com",
                        url = "http://localhost:8080"
                ),
                description = "OpenAPI documentation for spring security",
                title = "OpenAPI specification",
                version = "1.0",
                license = @License(
                        name = "License name",
                        url = "some-url.com"
                ),
                termsOfService = "terms & conditions"
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:8080"
                )
        },
        security = {
                @SecurityRequirement(
                        name = "nivethaAuth"
                )
        }

)

@SecurityScheme(
        name = "bearerAuth",
        description = "JWT auth description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER

)

//http://localhost:8080/otms/swagger-ui/index.html#/

public class OpenApiConfiguration {

}

