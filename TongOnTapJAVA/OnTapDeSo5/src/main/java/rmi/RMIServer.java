package rmi;

import dao.IQuizDAO;
import dao.QuestionDAO;
import dao.QuizDAO;
import model.Question;
import model.Quiz;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.rmi.registry.LocateRegistry;

public class RMIServer  {
    public static void main(String[] args) throws Exception{
        Context context = new InitialContext();
        LocateRegistry.createRegistry(7152);

        IQuizDAO quizDAO = new QuizDAO(Quiz.class);

        context.bind("rmi://GiaSi:7152/quizDAO", quizDAO);
    }
}
