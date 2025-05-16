package model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Table(name = "stores")
public class Store implements java.io.Serializable {
    @Id
    @jakarta.persistence.Column(name = "store_id")
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private int id;

    @jakarta.persistence.Column(name = "store_name")
    private String name;

    @Embedded
    private Contact contact;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "store")
    private java.util.List<Staff> staffs;

    @OneToMany(mappedBy = "store")
    private java.util.List<Stock> stocks;
}
