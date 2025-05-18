import dao.DepartmentDAO;
import dao.StudentDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import model.Department;
import model.Student;

public class Main {
    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("mariadb-pu")
                .createEntityManager();


        DepartmentDAO departmentDAO = new DepartmentDAO(Department.class);
        departmentDAO.getNumberOfStudentsByDepartment().forEach(((k, v) -> System.out.println(k.getName() + " " + v)));

        StudentDAO studentDAO = new StudentDAO(Student.class);
        studentDAO.getAverageScoreOfStudents().forEach(((k, v) -> System.out.println(k.getLastName() + " " + v)));

        departmentDAO.listDepartmentsWithoutStudents().forEach(System.out::println);
    }
}
