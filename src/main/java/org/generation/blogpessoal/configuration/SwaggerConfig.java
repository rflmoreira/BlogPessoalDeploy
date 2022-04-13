package org.generation.blogpessoal.configuration;


import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;

@Configuration /*indica que a Classe é do tipo
configuração, ou seja, define uma classe como fonte 
de definições de beans e é uma das
anotações essenciais se você estiver 
usando a configuração baseada em Java.*/

public class SwaggerConfig {

	@Bean /* utilizada em métodos de uma classe, geralmente marcada
com @Configuration, indica ao Spring que ele deve invocar aquele 
método e gerenciar o objeto retornado por ele, ou seja, agora 
este objeto pode ser injetado em qualquer 
ponto da sua aplicação.*/
	
	public OpenAPI springBlogPessoalOpenAPI() {
		
		return new OpenAPI()
				.info(new Info()
						.title("Projeto Blog Pessoal")
						.description("Porjeto Blog Pessoal - Generation Brasil")
						.version("v0.0.1")
				.license(new License()
						.name("Generation brasil")
						.url("https://brazil.generation.org/"))
				.contact(new Contact()
						.name("Conteudo Generation")
						.url("https://github.com/conteudogeneration")
						.email("conteudogeneration@gmail.com")))
				.externalDocs(new ExternalDocumentation()
						.description("Github")
						.url("https://github.com/conteudogeneration"));
		
	}
	
	@Bean
	public OpenApiCustomiser customerGlobalHeaderOpenApiCustomiser() {
		return openApi -> {
			openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {
				
				ApiResponses apiResponses = operation.getResponses();
				
				apiResponses.addApiResponse("200", createApiResponse("Sucesso!"));
				apiResponses.addApiResponse("201", createApiResponse("Objeto Persistido!"));
				apiResponses.addApiResponse("204", createApiResponse("Objeto Excluído!"));
				apiResponses.addApiResponse("400", createApiResponse("Erro na Requisição!"));
				apiResponses.addApiResponse("401", createApiResponse("Acesso Não Autorizado!"));
				apiResponses.addApiResponse("404", createApiResponse("Objeto Não Encontrado!"));
				apiResponses.addApiResponse("500", createApiResponse("Erro na Aplicação!"));
			}));
		};
	}

	private ApiResponse createApiResponse(String message) {
		return new ApiResponse().description(message);
	}
	
}
