package com.alexandria.dao;

public class DAOFactory {

    public AuthorDao getAuthorDao() { return new AuthorDao(); }
    public BookDao getBookDao() { return new BookDao(); }
    public CategoryDao getCategoryDao() { return new CategoryDao(); }
    public ClientDao getClientDao() { return new ClientDao(); }
    public CollectionDao getCollectionDao() { return new CollectionDao(); }
    public CountryDao getCountryDao() { return new CountryDao(); }
    public GenreDao getGenreDao() { return new GenreDao(); }
    public OrderHeaderDao getOrderHeaderDao() { return new OrderHeaderDao(); }
    public OrderLineDao getOrderLineDao() { return new OrderLineDao(); }
    public PaymentMethodDao getPaymentMethodDao() { return new PaymentMethodDao(); }
    public ProductDao getProductDao() { return new ProductDao(); }
    public PublisherDao getPublisherDao() { return new PublisherDao(); }
    public ShippingMethodDao getShippingMethodDao() { return new ShippingMethodDao(); }
    public TitleDao getTitleDao() { return new TitleDao(); }
}
