package model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@IdClass(Stock.StockID.class)
@Table(name = "stocks")
public class Stock implements java.io.Serializable {
    private int quantity;

    @Id
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Id
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StockID implements Serializable {
        private Store store;
        private Product product;
    }
}
