package dao;

public interface DaoInterface<T> {
	//ATTRIBUTI 
	
	//METODI
	public T retrieveByPK(String id);
	public void create(T object);
	public void update(T object);
	public void delete(String id);
}
