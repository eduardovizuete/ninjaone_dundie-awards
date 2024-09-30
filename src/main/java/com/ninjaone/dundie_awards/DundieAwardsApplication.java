package com.ninjaone.dundie_awards;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Dundie Award Spring Boot API Documentation",
				description = "Dundie Award Spring Boot API Documentation",
				version = "v1.0",
				contact = @Contact(
						name = "Contact Name",
						email = "email@email.email",
						url = "https://..."
				),
				license = @License(
						name= "Apache 2.0",
						url = "https://..."
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Dundie Award Spring Boot User Management Documentation",
				url = "https://..."
		)
)
public class DundieAwardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DundieAwardsApplication.class, args);
	}

}
