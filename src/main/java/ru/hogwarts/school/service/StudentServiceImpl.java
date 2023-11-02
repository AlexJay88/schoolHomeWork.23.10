package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.RepositoryStudent;

import java.util.Collection;

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
        return repositoryStudent.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("студент с id" + id + "не найден в хранилище"));

    }

    @Override
    public Student update(Student student) {
        repositoryStudent.findById(student.getId())
                .orElseThrow(() -> new StudentNotFoundException("студент с id" + student.getId() + "не найден в хранилище"));
        return repositoryStudent.save(student);
    }


    @Override

    public Student delete(long id) {
        Student student = read(id);
        repositoryStudent.delete(student);
        return student;

    }

    @Override
    public Collection<Student> readByAge(int age) {

        return repositoryStudent.findByAge(age);

    }

    @Override
    public Collection<Student> readByAgeBetween(int minAge, int maxAge) {
        return repositoryStudent.findByAgeBetween(minAge, maxAge);
    }

    @Override
    public Faculty readStudentFaculty(long studentId) {
        return read(studentId).getFaculty();
    }

    @Override
    public Collection<Student> readByFacultyId(long facultyId) {
        return repositoryStudent.findAllByFaculty_Id(facultyId);
    }


}

