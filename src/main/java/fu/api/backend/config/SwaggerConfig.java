package fu.api.backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {


    @Bean
    public GroupedOpenApi publicAPI(@Value("${open.api.doc}") String apiDocs) {
        return GroupedOpenApi.builder()
                .group(apiDocs)
                .packagesToScan("fu.api.backend.controller")
                .build();
    }

    @Bean
    public OpenAPI openAPI(
            @Value("${openapi.service.title}") String title,
            @Value("${openapi.service.version}") String version,
            @Value("${openapi.service.server}") String serverUrl
    ) {
        return new OpenAPI()
                .servers(List.of(new Server().url(serverUrl)))
                .info(new Info().title(title)
                        .description("API Documentation for backend service")
                        .version(version)
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://springdoc.org")));
    }

}
