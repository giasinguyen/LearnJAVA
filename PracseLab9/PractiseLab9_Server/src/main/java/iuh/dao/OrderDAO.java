package iuh.dao;

import iuh.model.Customer;
import iuh.model.Order;
import jakarta.persistence.EntityManager;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderDAO extends GenericDAO<Order, Integer> {
    public OrderDAO(EntityManager em, Class<Order> clazz) {
        super(em, clazz);
    }

    public OrderDAO(Class<Order> clazz) {
        super(clazz);
    }

    public double getOrderTotalById(int orderId) {
        String query = "SELECT SUM(oi.quantity * oi.listPrice * (1 - oi.discount)) " +
                "FROM OrderItem oi WHERE oi.order.id = :orderId";
        return em.createQuery(query, Double.class)
                .setParameter("orderId", orderId)
                .getSingleResult();
    }

    public Map<Customer, Long> getNumberOfOrdersForEachCustomer() {
        String query = "SELECT o.customer, Count(o) From Order o " +
                "Group By o.customer Order By Count(o) DESC";
        return em.createQuery(query, Object[].class).getResultList()
                .stream()
                .collect(Collectors.toMap(
                        result -> (Customer) result[0],
                        result -> (Long) result[1],
                        (a, b) -> a, LinkedHashMap::new
                ));
    }
}
