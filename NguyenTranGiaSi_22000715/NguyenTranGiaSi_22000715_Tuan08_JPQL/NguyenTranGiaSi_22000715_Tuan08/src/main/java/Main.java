import dao.impl.DepartmentDAOImpl;
import dao.impl.StudentDAOImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import model.Department;
import model.Student;

import java.time.LocalDateTime;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("mariadb-pu")
                .createEntityManager();

//        Student student = new Student();
//        student.setFirstName("Lan");
//        student.setLastName("Le");
//        student.setEnrollmentDate(LocalDateTime.of(2022, 1,1,0,0,0));
//
//        EntityTransaction tr = em.getTransaction();
//        tr.begin();
//        em.persist(student);
//
//        tr.commit();

//        DepartmentDAOImpl departmentDAO = new DepartmentDAOImpl(em, Department.class);
//        List<Department> departmentsWithoutStudents = departmentDAO.listDepartmentsWithoutStudents();
//
//        if (departmentsWithoutStudents.isEmpty()) {
//            System.out.println("No departments without students found.");
//        } else {
//            System.out.println("Departments without students:");
//            for (Department department : departmentsWithoutStudents) {
//                System.out.println("Department ID: " + department.getId() +
//                        ", Name: " + department.getName());
//            }
//        }
//
//        em.close();
        StudentDAOImpl studentDAO = new StudentDAOImpl(em, Student.class);
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
