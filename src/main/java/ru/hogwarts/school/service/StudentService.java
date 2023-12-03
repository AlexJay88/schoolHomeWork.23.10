package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.model.StudentsByCategory;

import java.util.Collection;
import java.util.List;

public interface StudentService {
    Student add(Student student);

    Student get(Long id);

    Student update(Long id, Student student);

    void remove(Long id);

    Collection<Student> getStudentByAge(int age);


    Collection<Student> getAllStudents();

    Collection<Student> getStudentsBetweenAge(int min, int max);


    Integer getStudentCount();

    Integer getAverageAgeOfStudents();

    Collection<StudentsByCategory> getLastFiveStudents();


    Integer getAverageAge();

    Student getById(Long studentId);

    Object getFacultyOfStudent(Long id);


    Collection<String> getAllStudentsFrom(String letter);

    List<String> getAllStudentsStream();

    List<String> getAllStudentsSynchronizedStream();

}