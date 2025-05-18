package model;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "questions")
public class Question implements java.io.Serializable {
    @Id
    @Column(name = "question_id")
    private String id;
    @Enumerated(EnumType.STRING)
    private Type type;
    @Column(name = "question_text")
    private String questionText;

    @Enumerated(EnumType.STRING)
    @Column(name = "question_level")
    private Level questionLevel;

    @ToString.Exclude
    @OneToMany(mappedBy = "question")
    private Set<Answer> answers;

    @ToString.Exclude
    @ManyToMany
    @JoinTable(name = "quizzes_questions", joinColumns = @JoinColumn(name = "question_id"), inverseJoinColumns = @JoinColumn(name = "quiz_id"))
    private Set<Quiz> quizzes;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}

