package dao;

import jakarta.persistence.EntityManager;
import model.Category;
import model.Level;
import model.Question;

import java.sql.SQLOutput;
import java.util.List;

public class QuestionDAO extends GenericDAO<Question, String> {

    public QuestionDAO(Class<Question> clazz) {
        super(clazz);
    }

    public QuestionDAO(EntityManager em, Class<Question> clazz) {
        super(em, clazz);
    }

//    listQuestionsByLevelAndCategory(categoryName: String, questionLevel: Level): List<Question>

    public List<Question> listQuestionsByLevelAndCategory(String categoryName, Level questionLevel) {
        String query = "Select q From Question q " +
                "Where q.questionLevel = :questionLevel and q.category.name = :categoryName ";
        return em.createQuery(query, Question.class)
                .setParameter("questionLevel", questionLevel)
                .setParameter("categoryName", categoryName)
                .getResultList();
    }

//    Thêm một câu hỏi (question) mới vào một chủ đề (category) cụ thể nào đó khi đã biết mã số chủ
//    đề. Thêm thành công sẽ trả về câu hỏi đã được thêm, ngược lại trả về null.
//            + addQuestionToCategory(question: Question, categoryId: String): Question

//    public Question addQuestionToCategory(Question question, String categoryId) {
//        String query = "select q from Question q where q.id = :questionId ";
//        Question q = em.createQuery(query, Question.class)
//                .setParameter("questionId", question.getId())
//                .getSingleResult();
//        if (q == null) return null;
//        q.setCategory(em.find(Category.class, categoryId));
//        return q;
//    }

    public Question addQuestionToCategory(Question question, String categoryId) {
        Category category = em.find(Category.class, categoryId);
        System.out.println(category);
        if (category == null) return null;
        else {
            QuestionDAO questionDAO = new QuestionDAO(em, Question.class);
            Question q = em.find(Question.class, question.getId());
            if (q == null) {
                question.setCategory(category);
                questionDAO.save(question);
                return question;
            } else {
                q.setCategory(category);
                questionDAO.update(q);
                return q;
            }
        }
    }
}
