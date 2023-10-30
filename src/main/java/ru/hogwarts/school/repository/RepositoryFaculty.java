package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

public interface RepositoryFaculty extends JpaRepository<Faculty, Long> {
    Collection<Faculty> findByColor(String color);

}