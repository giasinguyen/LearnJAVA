package model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)

@Entity
@DiscriminatorValue("Student")
public class Student extends Person implements Serializable {
    private LocalDateTime enrollmentDate;

    @OneToMany(mappedBy = "student")
    private Set<StudentGrade> studentGrades;

}