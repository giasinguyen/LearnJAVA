import dao.impl.StudentDAOImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import models.Student;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class MainApplication {
    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("mariadb-pu")
                .createEntityManager();

//        Student student = new Student();
//        student.setFirstName("Lan");
//        student.setLastName("Le");
//        student.setEnrollmentDate(LocalDateTime.of(2022, 1,1,0,0,0));
//
//        boolean result = studentDAO.save(student);
//        if(result){
//            System.out.println(student);
//        }else{
//            System.out.println("Error");
//        }

        StudentDAOImpl studentDAO = new StudentDAOImpl(em, Student.class);
        Map<Student, Double> averageScoreOfStudents = studentDAO.getAverageScoreOfStudents();
        for (Map.Entry<Student, Double> entry : averageScoreOfStudents.entrySet()) {
            Student student = entry.getKey();
            Double avgGrade = entry.getValue();
            System.out.println(student);
        }

//        String courseName = "Macroeconomics";
        String courseName = "Quantitative";

        List<Student> topStudents = studentDAO.listStudentsStudyingCourseWithHighestScore(courseName);

        if (topStudents.isEmpty()) {
            System.out.println("No students found with grades in course: " + courseName);
        } else {
            System.out.println("Top students in course \"" + courseName + "\":");
            for (Student student : topStudents) {
                System.out.println("Student ID: " + student.getId() +
                        ", Name: " + student.getFirstName() + " " + student.getLastName());
            }
        }

        em.close();

    }
}
