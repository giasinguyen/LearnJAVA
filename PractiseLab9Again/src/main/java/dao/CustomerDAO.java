package dao;

import jakarta.persistence.EntityManager;
import model.Customer;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CustomerDAO extends GenericDAO<Customer, Integer> {

    public CustomerDAO(Class<Customer> clazz) {
        super(clazz);
    }

    public CustomerDAO(EntityManager em, Class<Customer> clazz) {
        super(em, clazz);
    }

    public Map<String, Integer> getNumberCustomerByState(){
        String query = "Select c.address.state, Count(c) From Customer c " +
                "Where c.address.state is not null " +
                "Group By c.address.state ";
        return em.createQuery(query, Object[].class).getResultList().stream()
                .collect(Collectors.toMap(
                        o -> (String) o[0],
                        o -> ((Long) o[1]).intValue(),
                        (a, b) -> a, LinkedHashMap::new
                ));
    }
}
