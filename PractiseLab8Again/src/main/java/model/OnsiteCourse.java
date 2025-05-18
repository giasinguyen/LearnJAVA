package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

import java.sql.Time;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class OnsiteCourse extends Course implements java.io.Serializable {
    @Column(name = "Location")
    private String location;
    @Column(name = "Days")
    private String days;
    @Column(name = "Time")
    private Time time;
}
