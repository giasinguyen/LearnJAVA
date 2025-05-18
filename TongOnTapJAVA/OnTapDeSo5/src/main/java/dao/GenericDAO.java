package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import util.JPAUtil;

import java.io.Serializable;
import java.util.List;

public class GenericDAO<T, String> implements Serializable {
    protected EntityManager em; //not thread safe, stream - tuan tu
    private Class<T> clazz;

    public GenericDAO(Class<T> clazz){
        this.clazz = clazz;
        this.em = JPAUtil.getEntityManager();
    }

    public GenericDAO(EntityManager em, Class<T> clazz) {
        this.em = em;
        this.clazz = clazz;
    }

    public T findById(String id){
        return em.find(clazz, id);
    }

    public boolean save(T t){
        EntityTransaction tr = em.getTransaction();
        try{
            tr.begin();
            em.persist(t);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
            throw new RuntimeException(e);
        }
    }

    public boolean update(T t){
        EntityTransaction tr = em.getTransaction();
        try{
            tr.begin();
            em.merge(t);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
        }
        return false;
    }

    public boolean delete(String id){
        EntityTransaction tr = em.getTransaction();
        try{
            tr.begin();
            T t = em.find(clazz, id);
            em.remove(t);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
            throw new RuntimeException(e);
        }
    }

    public List<T> getAll(){
        return em.createQuery("Select t from " + clazz.getSimpleName() + " t").getResultList();
    }
}
