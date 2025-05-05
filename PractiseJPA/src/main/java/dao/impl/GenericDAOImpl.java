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

    public GenericDAOImpl(EntityManager em, Class<T> clazz) {
        this.em = em;
        this.clazz = clazz;
    }

    @Override
    public boolean save(T t) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(t);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
        }
        return false;
    }

    @Override
    public boolean update(T t) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(t);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
        }
        return false;
    }

    @Override
    public boolean delete(T id) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            T t = em.find(clazz, id);
            if(t != null) {
                em.remove(t);
                tx.commit();
                return true;
            }
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public T findById(ID id) {
        return em.find(clazz, id);
    }

    @Override
    public List<T> findAll() {
        return em.createQuery("select t from "+clazz.getSimpleName()+" t", clazz).getResultList();
    }
}
