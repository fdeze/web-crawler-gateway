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

		return builder.routes().route(r -> r.path("/getOffers/apec/**").uri("lb://apec-microservice"))
				.route(r -> r.path("/getOffers/adsearch/**").uri("lb://adsearch-microservice"))
				.route(r -> r.path("/getOffers/silkhom/**").uri("lb://silkhom-microservice")).build();
	}

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

}
