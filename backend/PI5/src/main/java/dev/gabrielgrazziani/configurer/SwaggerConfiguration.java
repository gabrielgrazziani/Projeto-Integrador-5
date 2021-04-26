package dev.gabrielgrazziani.configurer;

import java.util.function.Predicate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
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
		ApiInfoBuilder builder = new ApiInfoBuilder();
		
		builder.title("PI5 API");
		builder.description("Isto é uma aplicação RESTful de controle de ordems de serviço");
		builder.version("V1");
		builder.contact(new Contact("Github", "https://github.com/gabrielgrazziani/Projeto-Integrador-5", ""));
        return builder.build();
    }
		
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
	    registry.addRedirectViewController("swagger-ui.html", "swagger-ui/");
	    registry.addRedirectViewController("swagger-ui", "swagger-ui/");
	}
}
