package model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudentGrade implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int enrollmentID;
    @Column(name = "Grade")
    private double grade;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "CourseID")
    private Course course;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "StudentID")
    private Student student;
}
