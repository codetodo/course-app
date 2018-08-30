package com.codetodo.courseapp.dao.course;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<T extends Serializable> {
	List<T> findAll();

	T findById(Long id);

	Long create(T entity);

	void update(T entity);

	void delete(Long id);
}
