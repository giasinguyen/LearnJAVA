package model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "answers")
public class Answer implements Serializable {
    @Id
    @Column(name = "answer_id")
    private String id;
    @Column(name = "answer_text")
    private String answerText;
    @Column(name = "is_correct")
    private boolean isCorrect;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

}
