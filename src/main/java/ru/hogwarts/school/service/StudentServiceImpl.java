package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.RepositoryStudent;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
private final RepositoryStudent repositoryStudent;
    public StudentServiceImpl(RepositoryStudent repositoryStudent) {
        this.repositoryStudent = repositoryStudent;
    }




    @Override
    public Student create(Student student) {

     return repositoryStudent.save(student);

    }

    @Override
    public Student read(long id) {
     return repositoryStudent.findById(id).get();

    }

    @Override
    public Student update(Student student) {
    return repositoryStudent.save(student);

    }
    


    @Override

    public void delete(long id) {
        repositoryStudent.deleteById(id);

    }

    @Override
    public Collection  <Student> readByAge(int age) {

      return repositoryStudent.findByAge(age);


    }


}

