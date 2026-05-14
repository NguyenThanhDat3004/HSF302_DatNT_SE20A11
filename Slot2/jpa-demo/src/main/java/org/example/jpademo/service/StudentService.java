package org.example.jpademo.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.jpademo.entity.Student;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void createStudent(String fullName, String email, int age) {
        Student s = new Student(fullName, email, age);
        em.persist(s); // INSERT
        System.out.println("Saved with ID = " + s.getId());
    }

    @Transactional(readOnly = true)
    public void printAll() {
        em.createQuery("SELECT s FROM Student s", Student.class)
                .getResultList()
                .forEach(System.out::println);
    }

    // phuong thuc update 1 student
    @Transactional
    public void updateStudent(Long id, String fullName, String email, int age) {
        Student s = em.find(Student.class, id);
        if (s != null) {
            s.setFullName(fullName);
            s.setEmail(email);
            s.setAge(age);
        }
    }
}
