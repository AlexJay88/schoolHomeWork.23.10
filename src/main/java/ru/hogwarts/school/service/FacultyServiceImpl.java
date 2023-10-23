package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.FacultyNotFoundException;
import ru.hogwarts.school.exception.StudentAlreadyExistsException;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {
    private final Map<Long, Faculty> repositoryFaculty = new HashMap<>();
    private Long idCounterFaculty = 0L;

    @Override
    public Faculty create(Faculty faculty) {
        if (repositoryFaculty.containsValue(faculty)) {
            throw new StudentAlreadyExistsException("Факультет" + faculty + "уже есть в хранилище");
        }
        long id = ++idCounterFaculty;
        faculty.setId(id);


        repositoryFaculty.put(id, faculty);
        return faculty;

    }

    @Override
    public Faculty read(long id) {
        Faculty faculty = repositoryFaculty.get(id);
        if (faculty == null) {
            throw new FacultyNotFoundException("Факультет с id" + id + "не найден в хранилище");
        }
        return faculty;
    }

    @Override
    public Faculty update(Faculty faculty) {
        if (!repositoryFaculty.containsKey(faculty.getId())) {
            throw new FacultyNotFoundException("Факультет с id" + faculty.getId() + "не найден в хранилище");

        }


        repositoryFaculty.put(faculty.getId(), faculty);
        return faculty;

    }

    @Override
    public Faculty delete(long id) {
        Faculty faculty = repositoryFaculty.remove(id);
        if (faculty == null) {
            throw new FacultyNotFoundException("Факультет с id" + id + "не найден в хранилище");

        }
        return faculty;
    }

    @Override
    public Collection<Faculty> readByColor(String color) {
        return repositoryFaculty.values().stream()
                .filter(faculty -> Objects.equals(faculty.getColor(), color))
                .collect(Collectors.toUnmodifiableList());

    }
}