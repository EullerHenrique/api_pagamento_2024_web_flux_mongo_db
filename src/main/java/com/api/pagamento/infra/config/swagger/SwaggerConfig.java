package com.api.pagamento.infra.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Classe de configuração do Swagger
 *
 * @author Euller Henrique
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

	/**
	 * Define as configurações do Swagger
	 *
	 * @author Euller Henrique
	 * @return Docket
	 */
	@Bean
	public Docket customConfig() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.api.pagamento.controller"))
				.paths(PathSelectors.any()).build().apiInfo(apiInfo());
	}

	/**
	 * Define as informações da API
	 *
	 * @author Euller Henrique
	 * @return ApiInfo
	 */
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("API De Pagamento").description("Data de entrega: 06/08/24").version("2.0.0")
				.contact(new Contact("Euller Henrique", "https://github.com/EullerHenrique", "eullerhenrique@outlook.com")).build();
	}
}