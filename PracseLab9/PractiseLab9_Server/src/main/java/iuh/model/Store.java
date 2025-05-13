package iuh.model;

import jakarta.persistence.*;
import lombok.ToString;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor

@Entity
@AllArgsConstructor
@Table(name = "stores")
public class Store implements Serializable {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "store_id")
    private int id;

    @Column(name = "store_name")
    private String name;

    @Embedded
    private Address address;

    @Embedded
    private Contact contact;

    @OneToMany(mappedBy = "store")
    private List<Staff> staffs;

    @OneToMany(mappedBy = "store")
    private List<Stock> stocks;

}
