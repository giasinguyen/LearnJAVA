package model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Department implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DepartmentID")
    private int id;
    @Column(name = "Administrator")
    private int administrator;
    @Column(name = "Budget")
    private double budget;
    @Column(name = "Name")
    private String name;
    @Column(name = "StartDate")
    private LocalDateTime startDate;

    @ToString.Exclude
    @OneToMany(mappedBy = "department")
    private Set<Course> courses;
}
