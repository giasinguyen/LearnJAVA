package model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OfficeAssignment implements java.io.Serializable {
    @Column(name = "Location")
    private String location;
    @Column(name = "Timestamp")
    private Timestamp timestamp;

    @Id
    @OneToOne
    @JoinColumn(name = "InstructorID")
    private Instructor instructor;

}

