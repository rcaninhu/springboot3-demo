package net.rcan.demo.config

import io.swagger.v3.oas.models.ExternalDocumentation
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SpringDocsConfig {
    @Bean
    fun springShopOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info().title("SpringBoot 샘플 프로젝트")
                    .description("SpringBoot 샘플 프로젝트 입니다.")
                    .version("v0.0.1")
            )
            .externalDocs(
                ExternalDocumentation()
                    .description("springdoc 참고 문서")
                    .url("https://springdoc.org/#migrating-from-springfox")
            )
    }
}
