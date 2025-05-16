package model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @jakarta.persistence.Column(name = "staff_id"))
})
@Table(name = "staffs")
public class Staff extends Person implements java.io.Serializable {
    private byte active;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Staff manager;

    @ToString.Exclude
    @OneToMany(mappedBy = "manager")
    private List<Staff> staffs;

    @ToString.Exclude
    @OneToMany(mappedBy = "staff")
    private List<Order> orders;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

}
