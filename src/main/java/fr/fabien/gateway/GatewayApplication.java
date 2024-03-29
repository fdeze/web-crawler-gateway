package fr.fabien.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@SpringBootApplication
public class GatewayApplication {

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {

		return builder.routes().route(r -> r.path("/getOffers/apec/**").uri("lb://web-crawler-connector-apec"))
				.route(r -> r.path("/getOffers/ffg/**").uri("lb://ffg-microservice"))
				.route(r -> r.path("/getOffers/silkhom/**").uri("lb://web-crawler-connector-silkhom")).build();
	}

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

}
