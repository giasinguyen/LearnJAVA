import iuh.dao.CustomerDAO;
import iuh.dao.OrderDAO;
import iuh.dao.ProductDAO;
import iuh.model.Customer;
import iuh.model.Order;
import iuh.model.Product;
import iuh.util.JPAUtil;
import jakarta.persistence.EntityManager;

public class Main {
    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();

//        ProductDAO productDAO = new ProductDAO(Product.class);
//        productDAO.listProductsWithTheHighestPrice()
//                .forEach(p -> System.out.println(p));
//
//        productDAO.listProductsNotBeenSold2()
//                .forEach(p -> System.out.println(p));


        CustomerDAO customerDAO = new CustomerDAO(Customer.class);
        customerDAO.getNumberCustomerByState()
                .forEach((k, v) -> System.out.println(k + " : " + v));

        OrderDAO orderDAO = new OrderDAO(Order.class);
        orderDAO.getOrderTotalById(1);
        System.out.println(orderDAO.getOrderTotalById(1));

        orderDAO.getNumberOfOrdersForEachCustomer().forEach((k, v) -> System.out.println(k.getFirstName() + " " + k.getLastName() + " : " + v));

    }
}
