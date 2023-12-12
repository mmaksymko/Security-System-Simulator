package ua.lpnu.security_system_simulator;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(
				contact = @Contact(name = "Anna Huk, Liudmyla Minkovets, Maksym Myna, Oksana Holomsha, Veronika Moroz, Yaroslav Sierov, Yulian Danyliuk"),
				description = "OpenAPI documentaton for Security System Simulator aka SSS (try hachky)",
				title = "SSS"
		),
		servers = @Server(
				description = "Local Environment",
				url = "http://localhost:8080"
		)
)
@SpringBootApplication
public class SecuritySystemSimulatorApplication {
	public static void main(String[] args) {
		SpringApplication.run(SecuritySystemSimulatorApplication.class, args);
	}
}
