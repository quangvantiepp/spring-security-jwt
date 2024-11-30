package nature.sales_website.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;

public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        // Define Security Scheme
        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT") // Format of JWT token
                .name("Authorization") // Header name (Authorization)
                .description("Enter JWT token in the format: Bearer <your_token>");

        // Create OpenAPI with Security Scheme and Security Requirement
        return new OpenAPI()
                .components(new Components().addSecuritySchemes("bearerAuth", securityScheme)) // register security scheme
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth")) // apply security requirement
                .info(new Info()
                        .title("API Documentation")
                        .description("Swagger documentation with JWT authentication")
                        .version("1.0.0"));
    }
}
