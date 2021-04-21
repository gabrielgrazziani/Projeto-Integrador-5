package dev.gabrielgrazziani.configurer;

import java.util.Collections;
import java.util.function.Predicate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfiguration implements WebMvcConfigurer {

	@Bean
	public Docket api() {
		Predicate<RequestHandler> basePackage = RequestHandlerSelectors.basePackage("dev.gabrielgrazziani");
		Predicate<String> apiURLs = PathSelectors.ant("/**");
		return new Docket(DocumentationType.SWAGGER_2)
				.ignoredParameterTypes(Pageable.class)
				.select()
				.apis(basePackage)
				.paths(apiURLs)
				.build()
				.useDefaultResponseMessages(false)
				.apiInfo(getApiInfo());
	}
	
	private ApiInfo getApiInfo() {
        return new ApiInfo(
                "PI5 API",
                "Isto é uma aplicação RESTful de controle de ordems de serviço",
                "V1",
                "urn:tos",
                new Contact("Github", "https://github.com/gabrielgrazziani/Projeto-Integrador-5", ""),
                "CC BY-SA 3.0",
                "https://creativecommons.org/licenses/by-sa/3.0/",
                Collections.emptyList()
        );
    }
	
	@Bean
	public UiConfiguration uiConfig() {
	    return UiConfigurationBuilder
	            .builder()
	            .validatorUrl("swagger-ui.html")
	            .operationsSorter(OperationsSorter.METHOD)
	            .build();
	}
	
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
	    registry.addRedirectViewController("swagger-ui.html", "swagger-ui/");
	    registry.addRedirectViewController("swagger-ui", "swagger-ui/");
	}
}
