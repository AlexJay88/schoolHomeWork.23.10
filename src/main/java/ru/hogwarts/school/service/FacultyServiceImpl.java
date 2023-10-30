package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.FacultyNotFoundException;
import ru.hogwarts.school.exception.StudentAlreadyExistsException;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.RepositoryFaculty;
import ru.hogwarts.school.repository.RepositoryStudent;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {
    @Autowired
    private final RepositoryFaculty repositoryFaculty;

    public FacultyServiceImpl(RepositoryFaculty repositoryFaculty) {
        this.repositoryFaculty = repositoryFaculty;
    }


    @Override
    public Faculty create(Faculty faculty) {

        return repositoryFaculty.save(faculty);

    }

    @Override
    public Faculty read(long id) {


         return repositoryFaculty.findById(id).orElseThrow(()->  new FacultyNotFoundException("факультет с id"+id+"не найден в хранилище"));
    }

    @Override
    public Faculty update(Faculty faculty) {
         repositoryFaculty.findById(faculty.getId())
                .orElseThrow(()       ->  new FacultyNotFoundException("факультет с id"+faculty.getId()+"не найден в хранилище"));
         return   repositoryFaculty.save(faculty);
    }



    @Override
    public Faculty delete(long id) {
       Faculty faculty=read(id);
       repositoryFaculty.delete(faculty);
       return faculty;


    }


    @Override
    public Collection<Faculty> readByColor(String color) {
        return repositoryFaculty.findByColor(color);

    }
}
