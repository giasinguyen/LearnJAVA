
package iuh.dao;

import iuh.model.Customer;
import jakarta.persistence.EntityManager;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CustomerDAO extends GenericDAO<Customer, Integer> {

    public CustomerDAO(EntityManager em, Class<Customer> clazz) {
        super(em, clazz);
    }

    public CustomerDAO(Class<Customer> clazz) {
        super(clazz);
    }

    public Map<String, Long> getNumberCustomerByState(){
        String query = "SELECT c.address.state, COUNT(c) From Customer c " +
                "Where c.address.state IS NOT NULL " +
                "GROUP BY c.address.state ORDER BY COUNT(c) DESC";
        return em.createQuery(query, Object[].class).getResultList().stream()
                .collect(Collectors.toMap(
                        result -> (String) result[0],
                        result -> (Long) result[1],
                        (a, b) -> a, LinkedHashMap::new
                ));
    }

}
