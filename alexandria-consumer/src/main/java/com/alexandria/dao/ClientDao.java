package com.alexandria.dao;

import com.alexandria.entities.ClientEntity;

import java.util.List;

public interface ClientDao extends AbstractDao<ClientEntity> {

    void create(ClientEntity entity);

    void update(ClientEntity entity);

    void remove(ClientEntity entity);

    ClientEntity find(Object id);

    List<ClientEntity> findAll();

    List<ClientEntity> findRange(int[] range);

    List<ClientEntity> findFromFirstNameLastName(String name);

    List<ClientEntity> findFromEmailPassword(String email, String password);

    List<ClientEntity> findFromEmail(String email);

    int count();
}