import dao.StudentDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import model.Student;

public class Main {
    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("mariadb-pu")
                .createEntityManager();

        StudentDAO studentDAO = new StudentDAO(Student.class);
        studentDAO.getAverageScoreOfStudents().forEach((k, v) -> System.out.println(k + " : " + v));
    }
}
