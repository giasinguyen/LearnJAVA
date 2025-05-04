package models;

import jakarta.persistence.*;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Timestamp;

@NoArgsConstructor
@Setter
@Getter
@ToString

@Entity
public class OfficeAssignment implements Serializable {
    @Id
    @OneToOne
    @JoinColumn(name = "InstructorID", unique = true)
    private Instructor instructor;

    private String location;
    private Timestamp timestaTime;

}
