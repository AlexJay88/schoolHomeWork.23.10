package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;

import java.util.Optional;

public interface RepositoryAvatar extends JpaRepository<Avatar,Long> {
     Optional<Avatar> findByStudent_id(long studentId);

    }


