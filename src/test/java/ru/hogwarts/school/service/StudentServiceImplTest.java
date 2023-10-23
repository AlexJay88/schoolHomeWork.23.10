package ru.hogwarts.school.service;

import org.junit.jupiter.api.Test;
import ru.hogwarts.school.model.Student;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class StudentServiceImplTest {
     StudentServiceImpl studentService=new StudentServiceImpl();




    @Test
    void createStudent() {
        int id=1;
        String name="Harry";
        Collection<Student>students=studentService.getClass(Student);

    }

    @Test
    void read() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void readByAge() {
    }
}