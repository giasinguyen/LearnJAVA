package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "Department")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Department implements java.io.Serializable {
    @Column(name = "DepartmentID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "Name")
    private String name;
    @Column(name = "Administrator")
    private int administrator;
    @Column(name = "Budget")
    private double budget;
    @Column(name = "StartDate")
    private LocalDateTime startDate;


}
