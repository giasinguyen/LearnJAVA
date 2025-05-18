package dao;

import jakarta.persistence.EntityManager;
import model.Department;
import model.Student;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DepartmentDAO extends GenericDAO<Department, Integer> {
    public DepartmentDAO(EntityManager em, Class<Department> clazz) {
        super(em, clazz);
    }

    public DepartmentDAO(Class<Department> clazz) {
        super(clazz);
    }

    public Map<Department, Long> getNumberOfStudentsByDepartment (){
        String query = "Select c.department, count(sg.student) as number " +
                "From Course c inner join StudentGrade sg " +
                "on c.id = sg.course.id " +
                "group by c.department " +
                "order by number desc ";
        return em.createQuery(query, Object[].class).getResultList()
                .stream()
                .collect(
                        Collectors.toMap(
                                result -> (Department) result[0],
                                result -> (Long) result[1],
                                (a, b) -> a, LinkedHashMap::new
                        )
                );
    }

//    public List<Department> listDepartmentsWithoutStudents(){
//        String query = "select d from Department d " +
//                "where d.id not in (" +
//                "    select distinct c.id " +
//                "    from Course c " +
//                "    inner join StudentGrade sg on c.id = sg.id" +
//                ")";
//        return em.createQuery(query, Department.class).getResultList();
//    }

    public List<Department> listDepartmentsWithoutStudents() {
        String query = "SELECT d FROM Department d " +
                "WHERE d.id NOT IN (" +
                "   SELECT DISTINCT c.department.id " +
                "   FROM Course c " +
                "   JOIN c.studentGrades sg" +
                ")";
        return em.createQuery(query, Department.class).getResultList();
    }

}
