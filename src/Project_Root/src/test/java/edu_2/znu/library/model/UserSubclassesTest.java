package edu_2.znu.library.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserSubclassesTest {

    @Test
    void studentHasFieldsAndToString() {
        Student s = new Student("suser", "pass", "Ali", "ali@example.com");
        assertNotNull(s.id);
        assertEquals("suser", s.username);
        assertEquals("Ali", s.name);
        assertEquals("ali@example.com", s.email);
        assertTrue(s.toString().contains("Ali") || s.toString().contains("suser"));
    }

    @Test
    void employeeHasFieldsAndCounters() {
        Employee e = new Employee("euser", "pass", "Sara");
        assertNotNull(e.id);
        assertEquals("Sara", e.name);
        assertEquals(0, e.registeredBooks);
        assertEquals(0, e.approvedLoans);
        assertTrue(e.toString().contains("Sara"));
    }

    @Test
    void managerToString() {
        Manager m = new Manager("muser", "pass", "ManagerName");
        assertNotNull(m.id);
        assertEquals("ManagerName", m.name);
        assertTrue(m.toString().contains("مدیر") || m.toString().contains("ManagerName"));
    }
}
