package com.api.pagamento.infra.config.swagger;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Classe de configuração do Swagger
 *
 * @author Euller Henrique
*/
@Configuration
public class SwaggerConfig {

	@Bean
	public GroupedOpenApi customGroupedOpenApi() {
		return GroupedOpenApi.builder()
				.group("transacao")
				.packagesToScan("com.api.pagamento.app.controller")
				.pathsToMatch("/transacao/**")
				.build();
	}

	/*
	/**
	 * Define as informações da API
	 *
	 * @author Euller Henrique
	 * @return ApiInfo

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("API De Pagamento").description("Data de entrega: 06/08/24").version("2.0.0")
				.contact(new Contact("Euller Henrique", "https://github.com/EullerHenrique", "eullerhenrique@outlook.com")).build();
	}

	 */



}
