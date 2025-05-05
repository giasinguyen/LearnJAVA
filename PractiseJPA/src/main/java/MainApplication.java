import dao.impl.StudentDAOImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import models.Student;

import java.time.LocalDateTime;

public class MainApplication {
    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("mariadb-pu")
                .createEntityManager();
        Student student = new Student();
        student.setFirstName("Lan");
        student.setLastName("Le");
        student.setEnrollmentDate(LocalDateTime.of(2022, 1,1,0,0,0));

        StudentDAOImpl studentDAO = new StudentDAOImpl(em, Student.class);

        boolean result = studentDAO.save(student);
        if(result){
            System.out.println(student);
        }else{
            System.out.println("Error");
        }
    }
}
