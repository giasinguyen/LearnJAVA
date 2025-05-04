package models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@NoArgsConstructor
@Setter
@Getter
@ToString

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DepartmentID")
    private int id;
    @Column(name = "Name", columnDefinition = "nvarchar(50)")
    private String name;
    @Column(name = "Budget")
    private double budget;
    @Column(name = "StartDate")
    private LocalDateTime startDate;
    @Column(name = "Administrator")
    private int administrator;

}
