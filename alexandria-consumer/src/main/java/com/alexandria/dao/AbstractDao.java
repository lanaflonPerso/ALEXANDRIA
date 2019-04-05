package com.alexandria.dao;

import java.util.List;

public interface AbstractDao<T> {

	void create(T entity);

	void update(T entity);

	void remove(T entity);
	
	T find(Object id);

	List<T> findAll();

	List<T> findRange(int iMin, int nb);

	int count();
}
