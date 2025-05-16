package model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_items")
@Getter
@Setter
@IdClass(OrderItem.OrderItemId.class)
public class OrderItem implements Serializable {
    private int quantity;
    @Column(name = "list_price")
    private double listPrice;
    private double discount;

    @Id
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Id
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderItemId implements Serializable {
        private Order order;
        private Product product;
    }
}
