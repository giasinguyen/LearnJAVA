package edu.iuh.fit.dao;

import edu.iuh.fit.model.Customer;
import edu.iuh.fit.model.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderDAO extends GenericDAO<Order, Integer> {
    public OrderDAO(Class<Order> clazz) {
        super(clazz);
    }

    public OrderDAO(EntityManager em, Class<Order> clazz) {
        super(em, clazz);
    }

//  Calculate the total amount of all bills for a certain day.
    public double getTotalAmount(LocalDate orderDate){
        String jpql = "select sum(oi.quantity * (1 - oi.discount) * oi.listPrice) " +
                "from OrderItem oi " +
                "where oi.order.orderDate = :orderDate ";

        return em.createQuery(jpql, Double.class)
                .setParameter("orderDate", orderDate)
                .getSingleResult();
    }
    
    /**
     * Calculates the total amount for a specific order by its order ID.
     * The total is calculated as sum of: quantity * (1 - discount) * listPrice 
     * for each order item in the order.
     *
     * @param orderId The ID of the order to calculate total for
     * @return The total amount of the order, or 0.0 if the order has no items or doesn't exist
     */
    public double getOrderTotalById(int orderId) {
        String jpql = "SELECT SUM(oi.quantity * (1 - oi.discount) * oi.listPrice) " +
                      "FROM OrderItem oi " +
                      "WHERE oi.order.id = :orderId";
        
        try {
            Double result = em.createQuery(jpql, Double.class)
                    .setParameter("orderId", orderId)
                    .getSingleResult();
            
            return result != null ? result : 0.0;
        } catch (NoResultException e) {
            return 0.0; // Return 0 if no order items found
        }
    }
    
    /**
     * Counts the number of orders for each customer in the database.
     * 
     * @return A map with Customer objects as keys and the number of orders as values,
     *         ordered by customer ID
     */
    public Map<Customer, Long> getNumberOfOrdersForEachCustomer() {
        String jpql = "SELECT o.customer, COUNT(o) " +
                      "FROM Order o " +
                      "GROUP BY o.customer " +
                      "ORDER BY o.customer.id";
        
        List<Object[]> results = em.createQuery(jpql, Object[].class).getResultList();
        
        return results.stream()
                .collect(Collectors.toMap(
                    result -> (Customer) result[0],
                    result -> (Long) result[1],
                    (oldValue, newValue) -> oldValue,
                    LinkedHashMap::new
                ));
    }

    public static void main(String[] args) {
        OrderDAO orderDAO = new OrderDAO(Order.class);
        
        // Test existing method
        double totalForDate = orderDAO.getTotalAmount(LocalDate.of(2016,1,1));
        System.out.println("Total amount for Jan 1, 2016: $" + totalForDate);
        
        // Test new method with a few order IDs
        int[] orderIds = {1, 2, 3}; // Example order IDs to test
        for (int orderId : orderIds) {
            double orderTotal = orderDAO.getOrderTotalById(orderId);
            System.out.printf("Total amount for Order #%d: $%.2f\n", orderId, orderTotal);
        }
        
        // Test customer order count method
        System.out.println("\nNumber of orders per customer:");
        Map<Customer, Long> orderCountByCustomer = orderDAO.getNumberOfOrdersForEachCustomer();
        orderCountByCustomer.forEach((customer, count) -> 
            System.out.printf("Customer #%d (%s %s): %d orders\n", 
                customer.getId(), 
                customer.getFirstName(), 
                customer.getLastName(), 
                count));
    }
}
