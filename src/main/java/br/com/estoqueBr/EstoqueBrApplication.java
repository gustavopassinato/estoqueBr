package br.com.estoqueBr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import br.com.estoqueBr.service.TesteService;

@SpringBootApplication
public class EstoqueBrApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(EstoqueBrApplication.class, args);
		
		TesteService testeService = new TesteService();
		testeService.teste();
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(EstoqueBrApplication.class);
	}

}
