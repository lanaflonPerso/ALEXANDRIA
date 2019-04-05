package com.alexandria.managers;

import com.alexandria.entities.ClientEntity;
import com.alexandria.entities.CountryEntity;
import com.alexandria.entities.PaymentMethodEntity;
import com.alexandria.entities.TitleEntity;

import java.util.List;

public interface ClientManager {

    boolean isEmailAlreadyRegistered(String email);

    void register(ClientEntity client);

    ClientEntity validateClient(Login login);

    List<TitleEntity> getTitlesList();

    List<CountryEntity> getCountriesList();

    List<CountryEntity> getCountriesListRange(int iMin, int nb);

    List<PaymentMethodEntity> getPaymentMethodsList();

    List<PaymentMethodEntity> getPaymentMethodsListRange(int iMin, int nb);
}
