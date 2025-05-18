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
@ToString(callSuper = true)
@DiscriminatorValue("Instructor")
public class Instructor extends Person implements java.io.Serializable {
    @Column(name = "HireDate")
    private LocalDateTime hireDate;

    @ManyToMany
    @JoinTable(name = "CourseInstructor", joinColumns = @JoinColumn(name = "PersonID"), inverseJoinColumns = @JoinColumn(name = "CourseID"))
    private Set<Course> courses;
}
