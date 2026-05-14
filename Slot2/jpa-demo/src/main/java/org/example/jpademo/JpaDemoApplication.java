package org.example.jpademo;

import org.example.jpademo.service.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JpaDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(JpaDemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(StudentService service) {
        return args -> {
            service.createStudent("Nguyễn Văn A", "a@fpt.edu.vn", 20);
            service.createStudent("Trần Thị B", "b@fpt.edu.vn", 21);
            service.updateStudent(1L, "Nguyễn Văn A Updated", "a@fpt.edu.vn", 22);
            service.updateStudent(2L, "Trần Thị B Updated", "b@fpt.edu.vn", 23);
            service.printAll();
        };
    }
}
