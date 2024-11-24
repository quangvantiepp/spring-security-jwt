package nature.sales_website.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;


@Configuration
@EnableSwagger2
public class SpringFoxConfig {
    @Bean
    public Docket api() {

        // Create header Authorization
        Parameter authHeader= new springfox.documentation.builders.ParameterBuilder()
                .name("Authorization")
                .description("JWT token (Bearer + <token>)")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build();

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(Collections.singletonList(authHeader))// auto attached token to other api
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API Documentation")
                .description("API documentation with Swagger and JWT authentication")
                .version("1.0.0")
                .build();
    }
}