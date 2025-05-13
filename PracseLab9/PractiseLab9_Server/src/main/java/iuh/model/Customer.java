package iuh.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "customers",indexes = {
        @Index(name = "idx_customer_first_name", columnList = "first_name"),
        @Index(name = "idx_customer_email", columnList = "email"),
        @Index(name = "idx_customer_phone", columnList = "phone")
})
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "customer_id"))
})
public class Customer extends Person implements Serializable {
    @Embedded
    private Contact contact;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "customer")
    private java.util.List<Order> orders;

}
