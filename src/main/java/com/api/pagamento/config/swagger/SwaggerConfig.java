package com.api.pagamento.config.swagger;

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
 * Realiza um estorno
 *
 * @author Euller Henrique
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

	/**
	 * Realiza um estorno
	 *
	 * @author Euller Henrique
	 */
	@Bean
	public Docket customConfig() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.api.pagamento.controller"))
				.paths(PathSelectors.any()).build().apiInfo(apiInfo());
	}

	/**
	 * Realiza um estorno
	 *
	 * @author Euller Henrique
	 */
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("API De Pagamento").description("Data de entrega: 18/04/22").version("2024")
				.contact(new Contact("Euller Henrique", "https://github.com/EullerHenrique", "eullerhenrique@outlook.com")).build();
	}
}