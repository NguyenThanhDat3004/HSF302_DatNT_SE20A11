package org.example.jpademo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.jpademo.entity.Student;
import org.example.jpademo.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class StudentTest {

    @Autowired
    private StudentService studentService;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void testCreateAndRetrieveStudentFromDatabase() {
        // Tạo student mới
        studentService.createStudent("Test Student", "test@fpt.edu.vn", 25);
        entityManager.flush();
        entityManager.clear();

        // Lấy student với ID = 3L từ database
        // (Vì JpaDemoApplication đã thêm 2 student lúc khởi chạy)
        Student retrievedStudent = entityManager.find(Student.class, 3L);

        assertNotNull(retrievedStudent, "Student should exist in database");
        assertEquals(3L, retrievedStudent.getId());
        assertEquals("Test Student", retrievedStudent.getFullName());
        assertEquals("test@fpt.edu.vn", retrievedStudent.getEmail());
        assertEquals(25, retrievedStudent.getAge());
    }

    @Test
    public void testUpdateStudent() {
        studentService.updateStudent(1L, "Nguyễn Văn A Updated", "a@fpt.edu.vn", 22);
        entityManager.flush();
        entityManager.clear();
        Student updatedStudent = entityManager.find(Student.class, 1L);
        assertNotNull(updatedStudent, "Student should exist in database");
        assertEquals(1L, updatedStudent.getId());
        assertEquals("Nguyễn Văn A Updated", updatedStudent.getFullName());
        assertEquals("a@fpt.edu.vn", updatedStudent.getEmail());
        assertEquals(22, updatedStudent.getAge());
    }
}
