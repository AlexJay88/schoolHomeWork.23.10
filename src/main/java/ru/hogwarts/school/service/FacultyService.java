package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;

import java.util.Collection;

public interface FacultyService {
    Faculty create(Faculty faculty);


    Faculty read(long id);

    Faculty update(Faculty faculty);

    void delete(long id);

    Collection<Faculty> readByColor(String color);

}