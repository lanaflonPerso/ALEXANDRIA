package com.alexandria.dao;

public class DAOFactory {

    public AuthorDao getAuthorDao() { return new AuthorDaoImpl(); }
    public BookDao getBookDao() { return new BookDaoImpl(); }
    public CategoryDao getCategoryDao() { return new CategoryDaoImpl(); }
    public ClientDao getClientDao() { return new ClientDaoImpl(); }
    public CollectionDao getCollectionDao() { return new CollectionDaoImpl(); }
    public CountryDao getCountryDao() { return new CountryDaoImpl(); }
    public GenreDao getGenreDao() { return new GenreDaoImpl(); }
    public OrderHeaderDao getOrderHeaderDao() { return new OrderHeaderDaoImpl(); }
    public OrderLineDao getOrderLineDao() { return new OrderLineDaoImpl(); }
    public PaymentMethodDao getPaymentMethodDao() { return new PaymentMethodDaoImpl(); }
    public ProductDao getProductDao() { return new ProductDaoImpl(); }
    public PublisherDao getPublisherDao() { return new PublisherDaoImpl(); }
    public ShippingMethodDao getShippingMethodDao() { return new ShippingMethodDaoImpl(); }
    public TitleDao getTitleDao() { return new TitleDaoImpl(); }
}
