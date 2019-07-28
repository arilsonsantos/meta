package br.com.meta.avaliacao.gestaocontato.documentation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.SecurityScheme;
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
    public Docket docApi() {
        List<SecurityScheme> securitySchemes = new ArrayList<>();
        securitySchemes.add(new BasicAuth("basicAuth"));

        return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("br.com.meta.avaliacao.gestaocontato.controller"))
        .paths(PathSelectors.regex("/v1.*"))
        .build()
        .useDefaultResponseMessages(false)
        //.securitySchemes(securitySchemes)
        //.globalResponseMessage(RequestMethod.GET, responseMessageForGET())
        // .securityContexts(Arrays.asList(securityContext()))
        
        
        .globalOperationParameters(
            Arrays.asList(new ParameterBuilder()
            .name("basichAuth")
            .modelRef(new ModelRef("string"))
            .parameterType("header")
            .required(false)
            .description("Basic authorization")
            .build()))
                    
        .apiInfo(apiInfo());
                                
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Documentação da Gestão de Contatos")
                .description("Este documento permite testar a API de Gestão de Contatos")
                .version("1.0.0")
                .build();
    }
   

}