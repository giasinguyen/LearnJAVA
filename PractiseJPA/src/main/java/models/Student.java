package models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@ToString(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("Student")
public class Student extends Person implements Serializable {
    private LocalDateTime enrollmentDate;
}
