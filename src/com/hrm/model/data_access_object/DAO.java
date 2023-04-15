package com.hrm.model.data_access_object;

import javafx.collections.ObservableList;

public interface DAO<T> {
	ObservableList<T> getAll();

	boolean save(T t);

	boolean update(T t);

	boolean delete(T t);

}
