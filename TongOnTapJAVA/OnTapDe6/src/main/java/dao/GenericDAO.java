package dao;

import java.util.List;

public interface GenericDAO<T, ID> {
    T findById(ID id);
    boolean save(T t);
    boolean update(T t);
    boolean delete(ID id);
    List<T> getAll();
}
