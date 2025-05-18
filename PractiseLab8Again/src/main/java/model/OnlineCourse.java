package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class OnlineCourse extends Course implements java.io.Serializable {
    @Column(name = "URL")
    private String url;
}
