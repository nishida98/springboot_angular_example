package com.server.manager.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.manager.enumeration.Status;
import com.server.manager.model.Response;
import com.server.manager.model.Server;
import com.server.manager.service.implementation.ServerServiveImplementation;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
public class ServerController {

	private final ServerServiveImplementation serverService;
	
	@GetMapping("/list")
	public ResponseEntity<Response> getServers() {
		return ResponseEntity.ok(
				Response.builder()
					 .timeStamp(LocalDateTime.now())
					 .data(Map.of("servers", serverService.list(30)))
					 .message("Servers retrieved")
					 .status(HttpStatus.OK)
					 .statusCode(HttpStatus.OK.value())
					 .build());
	}
	
	@GetMapping("/ping/{idAddress}")
	public ResponseEntity<Response> pingServer(@PathVariable("idAddress") String idAddress) throws IOException {
		
		Server server = serverService.ping(idAddress);
		
		return ResponseEntity.ok(
				Response.builder()
					 .timeStamp(LocalDateTime.now())
					 .data(Map.of("server", server))
					 .message(server.getStatus().equals(Status.SERVER_UP) ? "Ping success" : "Ping failed")
					 .status(HttpStatus.OK)
					 .statusCode(HttpStatus.OK.value())
					 .build());
	}
	
	
	@PostMapping("/save")
	public ResponseEntity<Response> saveServer(@RequestBody @Valid Server server) {
		return ResponseEntity.ok(
				Response.builder()
					 .timeStamp(LocalDateTime.now())
					 .data(Map.of("server", serverService.create(server)))
					 .message("Server created!")
					 .status(HttpStatus.CREATED)
					 .statusCode(HttpStatus.CREATED.value())
					 .build());
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Response> getServer(@PathVariable("id") Long id) {
		return ResponseEntity.ok(
				Response.builder()
					 .timeStamp(LocalDateTime.now())
					 .data(Map.of("server", serverService.get(id)))
					 .message("Server retrieved")
					 .status(HttpStatus.OK)
					 .statusCode(HttpStatus.OK.value())
					 .build());
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Response> deleteServer(@PathVariable("id") Long id) {
		return ResponseEntity.ok(
				Response.builder()
					 .timeStamp(LocalDateTime.now())
					 .data(Map.of("deleted", serverService.delete(id)))
					 .message("Server deleted")
					 .status(HttpStatus.OK)
					 .statusCode(HttpStatus.OK.value())
					 .build());
	}
	
	@GetMapping(path = "/image/{fileName}", produces = MediaType.IMAGE_PNG_VALUE)
	public byte[] getServerImage(@PathVariable("fileName") String fileName) throws IOException {
		return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"Downloads/images/"+fileName));
	}
	
}
