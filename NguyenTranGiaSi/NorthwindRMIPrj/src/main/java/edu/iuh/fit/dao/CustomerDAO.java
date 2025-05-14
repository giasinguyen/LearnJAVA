package edu.iuh.fit.dao;

import edu.iuh.fit.model.Customer;
import jakarta.persistence.EntityManager;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CustomerDAO extends GenericDAO<Customer, Integer> {
    public CustomerDAO(Class<Customer> clazz) {
        super(clazz);
    }

    public CustomerDAO(EntityManager em, Class<Customer> clazz) {
        super(em, clazz);
    }
    
    /**
     * Returns a map containing the count of customers by state.
     * The map is sorted by state name in ascending order.
     *
     * @return Map with state as key and customer count as value
     */
    public Map<String, Long> getCustomerCountByState() {
        String query = "SELECT c.address.state, COUNT(c) " +
                       "FROM Customer c " +
                       "WHERE c.address.state IS NOT NULL " +
                       "GROUP BY c.address.state " +
                       "ORDER BY c.address.state ASC";
                       
        List<Object[]> results = em.createQuery(query, Object[].class).getResultList();
        
        // Convert the results to a LinkedHashMap to preserve the ordering
        Map<String, Long> customersByState = results.stream()
                .collect(Collectors.toMap(
                        result -> (String) result[0],
                        result -> (Long) result[1],
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
                ));
                
        return customersByState;
    }

    public static void main(String[] args) {
        CustomerDAO customerDAO = new CustomerDAO(Customer.class);
        Customer customer = customerDAO.findById(1);
        System.out.println(customer);
        
        System.out.println("\nCustomer Count by State:");
        Map<String, Long> stateCounts = customerDAO.getCustomerCountByState();
        stateCounts.forEach((state, count) -> 
            System.out.printf("%-20s : %d customers\n", state, count));
    }
}
