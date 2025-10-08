package dao;

import java.sql.SQLException;

public interface DaoInterface<T> {
	//ATTRIBUTI 
	
	//METODI
	default public T retrieveByPK(String id) {
		throw new UnsupportedOperationException("Method not implemented");
	}
	
	public void create(T object) throws SQLException;
	
	default public void update(T object) {
		throw new UnsupportedOperationException("Method not implemented");
	}
	
	default public void delete(String id) {
		throw new UnsupportedOperationException("Method not implemented");
	}
}
