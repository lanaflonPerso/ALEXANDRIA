package com.alexandria.managers;

import com.alexandria.dao.ClientDao;
import com.alexandria.dao.CountryDao;
import com.alexandria.dao.PaymentMethodDao;
import com.alexandria.dao.TitleDao;
import com.alexandria.entities.ClientEntity;
import com.alexandria.entities.CountryEntity;
import com.alexandria.entities.PaymentMethodEntity;
import com.alexandria.entities.TitleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

import java.util.List;

public class ClientManagerImpl implements ClientManager {

    @Autowired
    public ClientDao clientDao;

    @Autowired
    public TitleDao titleDao;

    @Autowired
    public CountryDao countryDao;

    @Autowired
    public PaymentMethodDao paymentMethodDao;

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

    @Override
    public List<TitleEntity> getTitlesList() { return titleDao.findAll(); }

    @Override
    public List<CountryEntity> getCountriesList() { return countryDao.findAll(); }

    @Override
    public List<PaymentMethodEntity> getPaymentMethodsList() { return paymentMethodDao.findAll(); }
}
