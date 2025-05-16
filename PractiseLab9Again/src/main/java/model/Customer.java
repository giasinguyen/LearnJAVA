package model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "customer_id"))
})
public class Customer extends Person implements java.io.Serializable {
    @Embedded
    private Address address;

    @OneToMany(mappedBy = "customer")
    private java.util.List<Order> orders;
}
