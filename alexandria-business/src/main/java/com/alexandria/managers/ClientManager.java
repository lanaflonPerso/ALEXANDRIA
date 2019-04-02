package com.alexandria.managers;

import com.alexandria.entities.ClientEntity;

public interface ClientManager {

    void register(ClientEntity client);

    ClientEntity validateClient(Login login);
}
