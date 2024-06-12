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
                .info(new Info().title("전북 알리미 짹짹 API")
                        .description("팀장 개인 Google Cloud Platform 인스턴스를 사용하여 배포하였습니다(http://34.64.170.83:8080) <br>" +
                                "현재 \"/\", \"/account/register/**\", \"/account/login\", \"/account/password/find\", \"/swagger-ui/**\", \"/v3/api-docs/**\" 를 제외한 모든 요청은 JWT 토큰이 있어야 접근 가능합니다. <br>" +
                                "데이터베이스는 따로 SQL을 작성하여 테이블을 생성하고 데이터를 넣은게 아닌, Spring Boot를 사용하여 자동적으로 데이터베이스를 생성하고, CSV를 Spring Boot에서 불러와서 데이터를 저장합니다. 따라서 초기 실행 시, application.properties에 데이터베이스 연결 정보를 설정하면 테이블 생성과 데이터 입력이 자동적으로 됩니다.")
                        .version("v.0.0.1"));
    }
}
