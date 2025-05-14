package iuh.dao;

import iuh.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public abstract class GenericDAO<T, ID> {
    protected EntityManager em;
    protected Class<T> clazz;

    public GenericDAO(EntityManager em, Class<T> clazz) {
        this.em = em;
        this.clazz = clazz;
    }

    public GenericDAO(Class<T> clazz) {
        this.clazz = clazz;
        this.em = JPAUtil.getEntityManager();
    }

    public T findById(ID id) {
        return em.find(clazz, id);
    }

    public List<T> getAll() {
        return em.createQuery("from " + clazz.getSimpleName(), clazz).getResultList();
    }

    public boolean save(T t) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.persist(t);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
            return false;
        }
    }

    public boolean update(T t) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.merge(t);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
            return false;
        }
    }

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
            if (tr.isActive()) {
                tr.rollback();
            }
            e.printStackTrace();
        }
        return false;
    }
}
