package model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories")
@ToString
public class Category implements java.io.Serializable {
    @Id
    @jakarta.persistence.Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "category_name")
    private String name;

    @ToString.Exclude
    @OneToMany(mappedBy = "category")
    private java.util.List<Product> products;
}
