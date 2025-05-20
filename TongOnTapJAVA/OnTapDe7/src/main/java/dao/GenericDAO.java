package dao;

import java.util.List;

public interface GenericDAO<T, ID>{
    public T findById(ID id);
    public List<T> getAll();
    public boolean save(T t);
    public boolean update(T t);
    public boolean delete(ID id);
}
