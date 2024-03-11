package Jeonbuk.contest.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI springJeonbukOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Jeonbuk Contest API")
                        .description("전북공모전 API 명세서 입니다")
                        .version("v.0.0.1"));
    }
}
