package model;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "categories")
public class Category implements java.io.Serializable {
    @Id
    @Column(name = "category_id")
    private String id;
    @Column(name = "category_name")
    private String name;
    private String description;

    @ToString.Exclude
    @OneToMany(mappedBy = "category")
    private Set<Question> questions;

    @OneToMany(mappedBy = "category")
    private Set<Quiz> quizzes;
}
