import iuh.dao.CustomerDAO;
import iuh.dao.OrderDAO;
import iuh.dao.OrderItemDAO;
import iuh.dao.ProductDAO;
import iuh.model.Customer;
import iuh.model.Order;
import iuh.model.OrderItem;
import iuh.model.Product;
import iuh.util.JPAUtil;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();

//        ProductDAO productDAO = new ProductDAO(Product.class);
//        productDAO.listProductsWithTheHighestPrice()
//                .forEach(p -> System.out.println(p));
//
//        productDAO.listProductsNotBeenSold2()
//                .forEach(p -> System.out.println(p));


//        CustomerDAO customerDAO = new CustomerDAO(Customer.class);
//        customerDAO.getNumberCustomerByState()
//                .forEach((k, v) -> System.out.println(k + " : " + v));
//
//        OrderDAO orderDAO = new OrderDAO(Order.class);
//        orderDAO.getOrderTotalById(1);
//        System.out.println(orderDAO.getOrderTotalById(1));
//
//        orderDAO.getNumberOfOrdersForEachCustomer().forEach((k, v) -> System.out.println(k.getFirstName() + " " + k.getLastName() + " : " + v));

        ProductDAO productDAO = new ProductDAO(Product.class);
        productDAO.getNumberOfProductsSoldForEachProduct().forEach((k, v) -> System.out.println(k.getName() + " : " + v));


        OrderItemDAO orderItemDAO = new OrderItemDAO(OrderItem.class);
        LocalDate anotherDate = LocalDate.of(2016, 1, 1);
        double anotherTotal = orderItemDAO.getTotalAmountForDate(anotherDate);
        System.out.printf("Total order amount for %s: $%.2f\n",
                anotherDate.toString(),
                anotherTotal);

        orderItemDAO.getTotalBillStatisticsByMonthYear(1, 2016).forEach((k, v) -> System.out.println(k + " : " + v));
        orderItemDAO.getTotalBillStatisticsByMonthYear2(1, 2016).forEach((k, v) -> System.out.println(k + " : " + v));
    }


}
