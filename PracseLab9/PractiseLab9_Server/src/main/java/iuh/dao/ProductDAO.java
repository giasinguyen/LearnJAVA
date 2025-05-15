package iuh.dao;

import iuh.model.Product;
import jakarta.persistence.EntityManager;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public List<Product> listProductsNotBeenSold() {
        String JPQL = "Select p From Product p " +
                "where SIZE(p.orderItems) = 0";
        return em.createQuery(JPQL, Product.class).getResultList();
    }

    public List<Product> listProductsNotBeenSold2() {
        String JPQL = "Select p From Product p LEFT JOIN OrderItem oi ON p.id = oi.product.id " +
                "where oi.product IS NULL";
        return em.createQuery(JPQL, Product.class).getResultList();
    }

    public Map<Product, Long> getNumberOfProductsSoldForEachProduct() {
        String query = "SELECT oi.product, COUNT(oi.quantity) FROM OrderItem oi " +
                "Group By oi.product " +
                "ORDER BY COUNT(oi) DESC";
        return em.createQuery(query, Object[].class).getResultList().stream().collect(
                Collectors.toMap(
                        result -> (Product) result[0],
                        result -> (Long) result[1],
                        (a, b) -> a, LinkedHashMap::new
                )
        );
    }

}
