package com.server.manager;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.server.manager.enumeration.Status;
import com.server.manager.model.Server;
import com.server.manager.repository.IServerRepository;

@SpringBootApplication
public class ServerManagerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerManagerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner run(IServerRepository serverRepo) {
		return args -> {
			serverRepo.save(new Server(null, "192.168.1.160", "Server Test", "16 GB", 
					"Teste", "image1", Status.SERVER_DOWN));
			serverRepo.save(new Server(null, "192.168.1.161", "Server Test 2", "16 GB", 
					"Teste 2", "image2", Status.SERVER_DOWN));
			serverRepo.save(new Server(null, "192.168.1.162", "Server Test 3", "16 GB", 
					"Teste 3", "image3", Status.SERVER_DOWN));
			serverRepo.save(new Server(null, "192.168.1.163", "Server Test 4", "16 GB", 
					"Teste 4", "image4", Status.SERVER_DOWN));
		};
	}
	
}
