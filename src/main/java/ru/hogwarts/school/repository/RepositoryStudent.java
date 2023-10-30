package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

public interface RepositoryStudent extends JpaRepository<Student, Long> {

    Collection<Student> findByAge(int age);
}


