package dao;

import jakarta.persistence.EntityManager;
import model.Order;
import model.OrderItem;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderDetailDAO extends GenericDAO<OrderItem, Integer>{

    public OrderDetailDAO(Class<OrderItem> clazz) {
        super(clazz);
    }

    public OrderDetailDAO(EntityManager em, Class<OrderItem> clazz) {
        super(em, clazz);
    }

    // Calculate the order's total amount when knowing the order number.
    public double getOrderTotalAmountByID(int orderId){
        String query = "Select Sum(oi.quantity * oi.listPrice * (1 - oi.discount)) From OrderItem oi " +
                "WHERE oi.order.id = :orderId ";
        return em.createQuery(query, Double.class).setParameter("orderId", orderId).getSingleResult();
    }
}
