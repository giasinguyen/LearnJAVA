package dao;

import jakarta.persistence.EntityManager;
import model.Student;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class StudentDAO extends GenericDAO<Student, Integer> {

    public StudentDAO(EntityManager em, Class<Student> clazz) {
        super(em, clazz);
    }

    public StudentDAO(Class<Student> clazz) {
        super(clazz);
    }

    public Map<Student, Double> getAverageScoreOfStudents() {
        String query = "Select sg.student, avg(sg.grade) as avgGrade " +
                "from  StudentGrade sg " +
                "where sg.grade is not null " +
                "group by sg.student ";
        return em.createQuery(query, Object[].class)
                .getResultList()
                .stream()
                .collect(Collectors.toMap(
                        result -> (Student) result[0],
                        result -> (Double) result[1]
                ));
    }

}
