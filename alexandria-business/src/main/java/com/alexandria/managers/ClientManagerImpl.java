package com.alexandria.managers;

import com.alexandria.dao.ClientDao;
import com.alexandria.entities.ClientEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

import java.util.List;

public class ClientManagerImpl implements ClientManager {

    @Autowired
    public ClientDao clientDao;

    @Override
    public void register(ClientEntity client) {
        clientDao.create(client);
    }

    @Override
    @Nullable
    public ClientEntity validateClient(Login login) {

        List<ClientEntity> clients = clientDao.findFromEmailPassword(login.getEmail(), login.getPassword());

        return clients.size() > 0 ? clients.get(0) : null;
    }
}
