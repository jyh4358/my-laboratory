package com.mylaboratory.spring.swagger.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    // 그룹이 1개일 경우 설정
//    @Bean
//    public OpenAPI springShopOpenAPI() {
//        return new OpenAPI()
//                .info(new Info().title("SpringShop API")
//                        .description("Spring shop sample application")
//                        .version("v0.0.1")
//                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
//                .externalDocs(new ExternalDocumentation()
//                        .description("SpringShop Wiki Documentation")
//                        .url("https://springshop.wiki.github.org/docs"));
////    }
//        return new OpenAPI()
//                .components(new Components()
//                        .addSecuritySchemes("access-key",
//                                new SecurityScheme().type(SecurityScheme.Type.APIKEY).in(SecurityScheme.In.HEADER).name("access-key"))
//                        .addSecuritySchemes("access-key2",
//                                new SecurityScheme().type(SecurityScheme.Type.APIKEY).in(SecurityScheme.In.HEADER).name("access-key2"))
//                        .addSecuritySchemes("access-key3",
//                                new SecurityScheme().type(SecurityScheme.Type.APIKEY).in(SecurityScheme.In.HEADER).name("access-key3")))
//                .security(List.of(new SecurityRequirement().addList("access-key")
//                        .addList("access-key2")
//                        .addList("access-key3"))
//                );
//
//    }

    // 그룹이 1개 이상일 경우 설정
    @Bean
    public GroupedOpenApi v1Api() {
        String[] path = new String[]{"/**/v1/**"};
        return GroupedOpenApi.builder()
                .group("v1")                // 그룹명
                .pathsToMatch(path)
                .displayName("v1 관련")              // 그룹명(노출)
                .addOpenApiCustomizer(
                        openApi ->
                                openApi
                                        .setInfo(new Info()
                                                .title("SpringShop API")                            // 제목
                                                .description("Spring shop sample application")      // 설명
                                                .version("v0.0.1")                                  // 버전
                                                .license(
                                                        new License().name("Apache 2.0").url("http://springdoc.org")
                                                )
                                        )


                )
                .build();
    }

    @Bean
    public GroupedOpenApi v2Api() {

        // Authentication 설정
        Components components = new Components()
                .addSecuritySchemes(
                        "access-key",
                        new SecurityScheme()
                                .type(SecurityScheme.Type.APIKEY)   // 어떤 Type인지
                                .in(SecurityScheme.In.HEADER)       // header, query, cookie
                                .name("access-key")                 // key name
                );

        // 해당 그룹의 정보 설정
        OpenApiCustomizer openApiCustomizer =
                openApi -> openApi.components(components)
                        .security(List.of(new SecurityRequirement().addList("access-key"))) // 등록된 Authentication 적용
                        .setInfo(
                                new Info()
                                        .title("SpringShop API2")
                                        .description("Spring shop sample application2")
                                        .version("v0.0.2")
                                        .license(new License().name("Apache 2.0").url("http://springdoc.org"))
                        );

        return GroupedOpenApi.builder()
                .group("v2")
                .pathsToMatch("/**/v2/**")
                .addOpenApiCustomizer(openApiCustomizer)
                .build();
    }
}
