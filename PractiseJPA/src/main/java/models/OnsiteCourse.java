package models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Time;

@Entity
@ToString(callSuper = true)

public class OnsiteCourse extends Course implements Serializable {
    @Column(name = "Location", columnDefinition = "nvarchar(50)")
    private String location;
    @Column(name = "Days", columnDefinition = "nvarchar(50)")
    private String days;
    @Column(name = "Time")
    private Time time;

}
