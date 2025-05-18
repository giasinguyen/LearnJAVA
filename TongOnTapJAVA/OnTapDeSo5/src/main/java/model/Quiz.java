package model;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "quizzes")
public class Quiz implements java.io.Serializable {
    @Id
    @Column(name = "quiz_id")
    private String id;
    private String title;
    private int score;


    @ToString.Exclude
    @ManyToMany(mappedBy = "quizzes")
    private Set<Question> questions;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;




}
