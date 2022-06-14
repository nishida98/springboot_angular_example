package com.server.manager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.manager.model.Server;

public interface IServerRepository extends JpaRepository<Server, Long>{

	Optional<Server> findByIpAddress(String ipAddress);
	
}
