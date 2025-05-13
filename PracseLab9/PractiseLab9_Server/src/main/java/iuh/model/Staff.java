package iuh.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Table(name = "staffs")
@AttributeOverride(name = "id", column = @jakarta.persistence.Column(name = "staff_id"))
@Entity
public class Staff extends Person implements Serializable {
    private byte active;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToMany(mappedBy = "staff")
    private java.util.List<Order> orders;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Staff manager;

    @ToString.Exclude
    @OneToMany(mappedBy = "manager")
    private List<Staff> staffs;

}
