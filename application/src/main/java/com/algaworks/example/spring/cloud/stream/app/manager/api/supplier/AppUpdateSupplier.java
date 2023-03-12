package com.algaworks.example.spring.cloud.stream.app.manager.api.supplier;

import java.util.UUID;
import java.util.function.Supplier;

import org.springframework.stereotype.Component;

import com.algaworks.example.spring.cloud.stream.app.manager.api.model.AppModel;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AppUpdateSupplier implements Supplier<AppModel> {

	@Override
	public AppModel get() {
		log.info("App Atualizado!");
		return AppModel.builder()
				.id(UUID.randomUUID())
				.name("Talisson Melo")
				.address("https://github.com/TalissonMelo/microservices_spring_cloud_stream_rabbitmq")
			.build();
	}

}
