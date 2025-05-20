package dao;

import jakarta.persistence.EntityManager;
import model.Level;
import model.Quiz;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class QuizDAO extends GenericDAO<Quiz, String> implements IQuizDAO {
    public QuizDAO(Class<Quiz> clazz) throws RemoteException {
        super(clazz);
    }

    public QuizDAO(EntityManager em, Class<Quiz> clazz) throws RemoteException{
        super(em, clazz);
    }

//    Thống kê số lượng câu hỏi theo mức độ khó của câu hỏi (question level) trong một bài trắc nghiệm
//(quiz) nào đó khi biết mã số bài trắc nghiệm, kết quả sắp xếp giảm dần theo số lượng câu hỏi.
// + countQuestionsByLevelInQuiz(quizId: String): Map<Level, Long>

    public Map<Level, Long> countQuestionsByLevelInQuiz(String quizId) throws RemoteException{
        String query = "SELECT q.questionLevel, COUNT(q) " +
                "FROM Quiz qz " +
                "JOIN qz.questions q " +
                "WHERE qz.id = :quizId " +
                "GROUP BY q.questionLevel " +
                "ORDER BY COUNT(q) DESC ";
        return em.createQuery(query, Object[].class).setParameter("quizId", quizId)
                .getResultList()
                .stream()
                .collect(
                        java.util.stream.Collectors.toMap(
                                o -> (Level) o[0],
                                o -> (Long) o[1],
                                (a, b) -> a, LinkedHashMap::new
                        )
                );
    }
}
