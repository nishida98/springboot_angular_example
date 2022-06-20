package com.server.manager.service.implementation;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.server.manager.enumeration.Status;
import com.server.manager.model.Server;
import com.server.manager.repository.IServerRepository;
import com.server.manager.service.IServerService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerServiveImplementation implements IServerService {

	private final IServerRepository serverRepository;
	
	@Override
	public Server create(Server server) {
		log.info("Saving new server: {}", server.getName());
		server.setImageUrl(setServerImageUrl());
		return serverRepository.save(server);
	}

	@Override
	public Server ping(String ipAddress) throws IOException {
		log.info("pinging server IP: {}", ipAddress);
		
		Server server = serverRepository.findByIpAddress(ipAddress).orElseThrow();
		
		InetAddress address = InetAddress.getByName(ipAddress);
		server.setStatus(address.isReachable(10000) ? Status.SERVER_UP : Status.SERVER_DOWN);
		
		serverRepository.save(server);
		
		return server;
	}

	@Override
	public Collection<Server> list(int limit) {
		log.info("Fetching all servers");
		return serverRepository.findAll(PageRequest.of(0, limit)).toList();
	}

	@Override
	public Server get(Long id) {
		log.info("Fetching server by id: {}", id);
		return serverRepository.findById(id).get();
	}

	@Override
	public Server update(Server server) {
		log.info("Updating server: {}", server.getName());
		return serverRepository.save(server);
	}

	@Override
	public Boolean delete(Long id) {
		log.info("Deleting server by id: {}", id);
		serverRepository.deleteById(id );
		return true;
	}
	
	private String setServerImageUrl() {
		return null;
	}
}
