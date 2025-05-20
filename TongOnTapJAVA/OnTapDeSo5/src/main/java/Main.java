import dao.QuestionDAO;
import dao.QuizDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import model.Level;
import model.Question;
import model.Quiz;
import util.JPAUtil;

import java.rmi.RemoteException;

public class Main {
    public static void main(String[] args) throws RemoteException {
        EntityManager em = JPAUtil.getEntityManager();

        QuestionDAO questionDAO = new QuestionDAO(em, Question.class);
        questionDAO.listQuestionsByLevelAndCategory("Movies", Level.HARD).forEach(System.out::println);

        QuizDAO quizDAO = new QuizDAO(Quiz.class);
        quizDAO.countQuestionsByLevelInQuiz("QZ103").forEach((k, v) -> System.out.println(k + " : " + v));

        Question question = new Question();
        question.setId("QZ600");
        question.setQuestionText("What is the name of the first movie in the Marvel Cinematic Universe?");
        question.setQuestionLevel(Level.HARD);
        question.setType(model.Type.FILL_IN_THE_BLANK);
        System.out.println(questionDAO.addQuestionToCategory(question, "C101"));
//        questionDAO.addQuestionToCategory(question, "Movies");
    }
}
