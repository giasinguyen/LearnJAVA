package model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
@Getter
@Setter
@ToString
public class Product implements java.io.Serializable {
    @Id
    @jakarta.persistence.Column(name = "product_id")
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private int id;
    @Column(name = "product_name")
    private String name;
    @Column(name = "model_year")
    private int modelYear;
    @Column(name = "list_price")
    private double listPrice;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "category_id")
    private Category category;
}
