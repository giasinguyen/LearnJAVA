package model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Course implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CourseID")
    protected int id;
    @Column(name = "Title")
    protected String title;
    @Column(name = "Credits")
    protected int credits;

    @ToString.Exclude
    @OneToMany(mappedBy = "course")
    private Set<StudentGrade> studentGrades;

    @ManyToOne
    @JoinColumn(name = "DepartmentID")
    private Department department;

    @ManyToMany(mappedBy = "courses")
    private Set<Instructor> instructors;
}
