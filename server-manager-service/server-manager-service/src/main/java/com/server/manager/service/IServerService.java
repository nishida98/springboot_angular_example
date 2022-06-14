package com.server.manager.service;

import java.util.Collection;

import com.server.manager.model.Server;

public interface IServerService {

	Server create(Server server);
	Collection<Server> list(int limit);
	Server get(Long id);
	Server update(Server server);
	Boolean delete(Long id);
	
}
