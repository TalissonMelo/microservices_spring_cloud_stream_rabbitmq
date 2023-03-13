package com.talissonmelo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	public RouteLocator routes(RouteLocatorBuilder routeLocatorBuilder) {
		return  routeLocatorBuilder.routes()
				.route( r -> r.path("/v1/apps/**").uri("lb://ms-application"))
				.route( r -> r.path("/v1/metrics/**").uri("lb://ms-metric"))
				.route( r -> r.path("/v1/schedules/**").uri("lb://ms-scheduler"))
				.build();
	}
}
