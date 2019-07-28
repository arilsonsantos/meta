package br.com.meta.avaliacao.gestaocontato.documentation;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * SwaggerConfig
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket authTokenSecuredApi() {
      return new Docket(DocumentationType.SWAGGER_2)
        .groupName("authTokenGroup") // 2 Dockets -> need to differ using groupName
        .select()
        .apis(RequestHandlerSelectors.basePackage("br.com.meta.avaliacao.gestaocontato.controller"))
        .paths(PathSelectors.any())
        .build()
        .securitySchemes(Collections.singletonList(new ApiKey("X-Auth-Token", 
                                                              "xAuthToken",
                                                              "header")))
        .securityContexts(Collections.singletonList(xAuthTokenSecurityContext()));
    }

    @Bean
    public Docket basicAuthSecuredApi() {
      return new Docket(DocumentationType.SWAGGER_2)
        .groupName("basicAuthGroup") // 2 Dockets -> need to differ using groupName
        .select()
        .apis(RequestHandlerSelectors.basePackage("br.com.meta.avaliacao.gestaocontato.controller"))
        .paths(PathSelectors.any())
        .build()
        .securitySchemes(Collections.singletonList(new BasicAuth("xBasic")))
        .securityContexts(Collections.singletonList(xBasicSecurityContext()));
    }

    private SecurityContext xBasicSecurityContext() {
      return SecurityContext.builder()
        .securityReferences(Collections.singletonList(
                              new SecurityReference("xBasic", 
                                                    new AuthorizationScope[0])))
        .build();
    }

    private SecurityContext xAuthTokenSecurityContext() {
      return SecurityContext.builder()
        .securityReferences(Collections.singletonList(
                              new SecurityReference("xAuthToken", 
                                                    new AuthorizationScope[0])))
        .build();
    }
                                
}