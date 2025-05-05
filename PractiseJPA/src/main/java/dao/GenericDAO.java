package dao;

import java.util.List;

public interface GenericDAO <T, ID>{
    boolean save(T t);
    boolean update(T t);
    boolean delete(T t);
    T findById(ID id);
    List<T> findAll();

}
