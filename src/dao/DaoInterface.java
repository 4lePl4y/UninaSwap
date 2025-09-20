package dao;

import exception.CustomSQLException;

public interface DaoInterface<T> {
	//ATTRIBUTI 
	
	//METODI
	default public T retrieveByPK(String id) {
		throw new UnsupportedOperationException("Method not implemented");
	}
	
	public void create(T object) throws CustomSQLException;
	public void update(T object);
	default public void delete(String id) {
		throw new UnsupportedOperationException("Method not implemented");
	}
}
