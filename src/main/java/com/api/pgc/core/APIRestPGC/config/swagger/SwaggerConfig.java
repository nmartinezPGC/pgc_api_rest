package com.api.pgc.core.APIRestPGC.config.swagger;


import com.api.pgc.core.APIRestPGC.utilities.configAPI;
import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.PathProvider;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletContext;

import java.util.Arrays;
import java.util.List;

import static com.api.pgc.core.APIRestPGC.utilities.configAPI.API_BASE_PATH;
import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@Configuration
public class SwaggerConfig implements WebMvcConfigurer {

    // Constantes de la API
    private configAPI configApi;
    private ServletContext servletContext;

    //Inicializacion de Swagger
    @Bean
    public Docket pgcApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                // .pathMapping("nam")
                .pathProvider(pathProvider())
                .groupName("api-pgc")
                .select()
                    .apis(RequestHandlerSelectors.basePackage("com.api.pgc.core.APIRestPGC"))
                    .paths(regex( configAPI.API_BASE_PATH + ".*"))
                    .build()
                .securitySchemes(Lists.newArrayList(apiKey()))//Habilita la Inclucion de Autorization, para los EndPoint que lo Solicitan
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {
        ApiInfo ApiInfo = new ApiInfoBuilder().title("Documentación de la API PGC")
                .description("Secretaría de Relaciones Exteriores y Cooperación Internacional (SRECI) | API PGC")
                // .termsOfServiceUrl("")
                .contact(new Contact("Nahúm Martinez", "http://pgc.sre.gob.hn", "nahum.sreci@gmail.com"))
                // .license("Apache 2.0")
                //.licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .version("2.0.0")
                .build();
        //Return de Api
        return ApiInfo;
    }

    /**
     * Genera el Encabezado de la key de la API
     * @return
     */
    private ApiKey apiKey() {
        return new ApiKey("Token-PGC", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("/api.*"))
                .build();
    }


    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("Token-PGC", authorizationScopes));
    }


    @Bean
    public SecurityConfiguration security() {
        return SecurityConfigurationBuilder.builder()
                .clientId("ABC")
                .clientSecret("ABC")
                .realm("realm")
                .appName("test-app")
                .scopeSeparator(",")
                .additionalQueryStringParams(null)
                .useBasicAuthenticationWithAccessCodeGrant(false)
                .build();
    }

    private PathProvider pathProvider() {
        return new RelativePathProvider(servletContext) {
            @Override
           protected String applicationPath() {
                return "/API-Rest-PGC";
                //return "/";
            }
        };
    }

}
