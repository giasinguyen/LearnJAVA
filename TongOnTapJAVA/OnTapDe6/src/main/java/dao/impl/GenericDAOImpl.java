package dao.impl;

import dao.GenericDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import util.JPAUtil;

import java.util.List;

public abstract class GenericDAOImpl<T, ID> implements GenericDAO<T, ID> {
    protected EntityManager em;
    private Class<T> clazz;

    public GenericDAOImpl(Class<T> clazz) {
        this.clazz = clazz;
        this.em = JPAUtil.getEntityManager();
    }

    public GenericDAOImpl(Class<T> clazz, EntityManager em) {
        this.clazz = clazz;
        this.em = em;
    }

    @Override
    public T findById(ID id) {
        return em.find(clazz, id);
    }

    @Override
    public boolean save(T t) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.persist(t);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(T t) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.merge(t);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(ID id) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            T t = em.find(clazz, id);
            if (t != null) {
                em.remove(t);
                tr.commit();
                return true;
            }
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<T> getAll() {
        return em.createQuery("SELECT t from " + clazz.getSimpleName() + " t", clazz).getResultList();
    }
}
