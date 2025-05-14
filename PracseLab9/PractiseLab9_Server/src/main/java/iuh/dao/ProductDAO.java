package iuh.dao;

import iuh.model.Product;
import jakarta.persistence.EntityManager;

import java.util.List;

import static org.hibernate.FetchMode.JOIN;

public class ProductDAO extends GenericDAO<Product, Integer> {

    public ProductDAO(EntityManager em, Class<Product> clazz) {
        super(em, clazz);
    }

    public ProductDAO(Class<Product> clazz) {
        super(clazz);
    }

    public List<Product> listProductsWithTheHighestPrice() {
        String JPQL = "SELECT p FROM Product p " +
                "where p.listPrice = (select max(p.listPrice) from Product p)";
        return em.createQuery(JPQL, Product.class).getResultList();
    }

    public List<Product> listProductsNotBeenSold(){
        String JPQL = "Select p From Product p " +
                "where SIZE(p.orderItems) = 0";
        return em.createQuery(JPQL, Product.class).getResultList();
    }

    public List<Product> listProductsNotBeenSold2(){
        String JPQL = "Select p From Product p LEFT JOIN OrderItem oi ON p.id = oi.product.id " +
                "where oi.product IS NULL";
        return em.createQuery(JPQL, Product.class).getResultList();
    }

}
