package model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "brands")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Brand implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    private int id;
    @Column(name = "brand_name")
    private String name;

    @ToString.Exclude
    @OneToMany(mappedBy = "brand")
    private java.util.List<Product> products;
}
