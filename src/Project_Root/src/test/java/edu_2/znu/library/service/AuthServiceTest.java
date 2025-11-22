package edu_2.znu.library.service;

import edu_2.znu.library.model.Student;
import edu_2.znu.library.model.Employee;
import edu_2.znu.library.model.Manager;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class AuthServiceTest {

    @Test
    void registerStudentAndGetList() {
        List<Student> students = new ArrayList<>();
        List<Employee> employees = new ArrayList<>();
        List<Manager> managers = new ArrayList<>();
        AuthService auth = new AuthService(students, employees, managers);

        Student s = auth.registerStudent("john", "pw", "John Doe", "john@example.com");
        assertNotNull(s);
        assertEquals(1, auth.getStudents().size());
        assertEquals("john", auth.getStudents().get(0).username);
    }
}
