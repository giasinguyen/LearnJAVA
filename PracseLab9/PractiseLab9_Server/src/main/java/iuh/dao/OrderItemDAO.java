package iuh.dao;

import iuh.model.OrderItem;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderItemDAO extends GenericDAO<OrderItem, Integer> {

    public OrderItemDAO(EntityManager em, Class<OrderItem> clazz) {
        super(em, clazz);
    }

    public OrderItemDAO(Class<OrderItem> clazz) {
        super(clazz);
    }

    public double getTotalAmountForDate(LocalDate orLocalDate){
        String query = "Select SUM(oi.quantity * oi.listPrice * (1 - oi.discount)) " +
                "FROM OrderItem oi Where oi.order.orderDate = :orLocalDate";
        return em.createQuery(query, Double.class).setParameter("orLocalDate", orLocalDate).getSingleResult();
    }

    public Map<String, Double> getTotalBillStatisticsByMonthYear(int month, int year){
        String query = "SELECT FUNCTION('YEAR', oi.order.orderDate), FUNCTION('MONTH', oi.order.orderDate), " +
                "SUM(oi.quantity * oi.listPrice * (1 - oi.discount)) " +
                "FROM OrderItem oi " +
                "WHERE FUNCTION('YEAR', oi.order.orderDate) = :year " +
                "AND FUNCTION('MONTH', oi.order.orderDate) = :month " +
                "GROUP BY FUNCTION('YEAR', oi.order.orderDate), FUNCTION('MONTH', oi.order.orderDate) ";
        return em.createQuery(query, Object[].class)
                .setParameter("year", year)
                .setParameter("month", month)
                .getResultList().stream()
                .collect(Collectors.toMap(
                        result -> result[0].toString() + "-" + result[1].toString(),
                        result -> (Double) result[2],
                        (a, b)-> a, LinkedHashMap::new
                ));
    }

    /**
     * Tính tổng giá trị hóa đơn theo tháng/năm được chỉ định.
     *
     * @param month tháng cần thống kê (1-12)
     * @param year năm cần thống kê
     * @return Map với khóa là "YYYY-MM" và giá trị là tổng doanh thu
     */
    public Map<String, Double> getTotalBillStatisticsByMonthYear2(int month, int year) {
        String query = "SELECT CONCAT(FUNCTION('YEAR', oi.order.orderDate), '-', " +
                "FUNCTION('MONTH', oi.order.orderDate)), " +
                "SUM(oi.quantity * oi.listPrice * (1 - oi.discount)) " +
                "FROM OrderItem oi " +
                "WHERE FUNCTION('YEAR', oi.order.orderDate) = :year " +
                "AND FUNCTION('MONTH', oi.order.orderDate) = :month " +
                "GROUP BY FUNCTION('YEAR', oi.order.orderDate), FUNCTION('MONTH', oi.order.orderDate)";

        return em.createQuery(query, Object[].class)
                .setParameter("year", year)
                .setParameter("month", month)
                .getResultList().stream()
                .collect(Collectors.toMap(
                        result -> (String) result[0],
                        result -> (Double) result[1],
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
    }
}
