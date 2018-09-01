package com.codetodo.courseapp.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<T extends Serializable> {
	List<T> findAll();

	T findById(Long id);

	void create(T entity);

	void update(T entity);

	void delete(Long id);
}