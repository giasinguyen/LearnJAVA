package dao;

import jakarta.persistence.EntityManager;
import model.Department;
import model.Student;

import java.util.Map;

public class StudentDAO extends GenericDAO<Student, Integer> {

    public StudentDAO(EntityManager em, Class<Student> clazz) {
        super(em, clazz);
    }

    public StudentDAO(Class<Student> clazz) {
        super(clazz);
    }

    public Map<Student, Double> getAverageScoreOfStudents(){
        String query = "Select sg.student, avg(sg.grade) as average " +
                "from StudentGrade sg " +
                "Where sg.grade is not null " +
                "group by sg.student " +
                "order by average desc ";
        return em.createQuery(query, Object[].class).getResultList()
                .stream()
                .collect(
                        java.util.stream.Collectors.toMap(
                                result -> (Student) result[0],
                                result -> (Double) result[1],
                                (a, b) -> a, java.util.LinkedHashMap::new
                        )
                );
    }
}
