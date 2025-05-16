import dao.CustomerDAO;
import dao.ProductDAO;
import jakarta.persistence.EntityManager;
import model.Customer;
import model.Product;
import util.JPAUtil;

public class Main {
    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();

        ProductDAO dao = new ProductDAO(em, Product.class);
        dao.listProductsNotBeenSold().forEach(System.out::println);

        CustomerDAO customerDAO = new CustomerDAO(em, Customer.class);
        customerDAO.getNumberCustomerByState().forEach((k, v) -> System.out.println(k + " : " + v));
    }
}
