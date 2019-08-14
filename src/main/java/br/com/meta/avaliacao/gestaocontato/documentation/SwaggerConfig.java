package br.com.meta.avaliacao.gestaocontato.documentation;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.meta.avaliacao.gestaocontato.util.TokenUtil;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * SwaggerConfig
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket basicAuthSecuredApi() {
      return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("br.com.meta.avaliacao.gestaocontato.controller"))
        .paths(PathSelectors.any())
        .build()
        .globalOperationParameters(Collections.singletonList(
                new ParameterBuilder()
                        .name("Authorization")
                        .description("Bearer token")
                        .modelRef(new ModelRef("string"))
                        .parameterType("header")
                        .required(true)
                        .build()))
        .apiInfo(apiInfo());
    }
   

    private ApiInfo apiInfo() {
        String adminToken = new TokenUtil().getToken("admin");
        return new ApiInfoBuilder().title("Documentação da API Gestão de Contatos")
        .description("Este documento permite testar a API de Gestão de Contatos \n\n" + 
            "Para testar a execução dos métodos, é necessário a utilização de um token, use os tokens abaixo: \n\n" +
            adminToken + "\n\n" +
             "Ou utilize o comando abaixo ou use o POSTMAN para geração do token. \n\n\n" +
            "curl -d '{\"username\":\"admin\", \"password\":\"123\"}' " +
            "-H \"Content-Type: application/json\" -X POST http://localhost:8080/gestaocontato/login")
                .version("1.0.0")
        .build();
    }
   

}