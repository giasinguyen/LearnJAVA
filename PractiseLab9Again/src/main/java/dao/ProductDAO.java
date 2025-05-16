package dao;

import jakarta.persistence.EntityManager;
import model.Product;

import java.util.List;
import java.util.Map;

public class ProductDAO extends GenericDAO<Product, Integer> {

    public ProductDAO(Class<Product> clazz) {
        super(clazz);
    }

    public ProductDAO(EntityManager em, Class<Product> clazz) {
        super(em, clazz);
    }

    public List<Product> getProductsWithTheHighestPrice(){
        String query = "Select p from Product p " +
                "Where p.listPrice = (Select Max(p2.listPrice) From Product p2) ";
        return em.createQuery(query, Product.class).getResultList();
    }

    public List<Product> listProductsNotBeenSold(){
        String query = "Select p from Product p " +
                "left join OrderItem oi ON p.id = oi.product.id " +
                "Where oi.product IS NULL";
        return em.createQuery(query, Product.class).getResultList();
    }

}

