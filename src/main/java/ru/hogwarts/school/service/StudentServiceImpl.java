package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.StudentAlreadyExistsException;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private final Map<Long, Student> repositoryStudent = new HashMap<>();

    private Long idCounterStudent = 0L;

    @Override
    public Student create(Student student) {
        if (repositoryStudent.containsValue(student)) {
            throw new StudentAlreadyExistsException("Студент" + student + "уже есть в хранилище");
        }
        long id = ++idCounterStudent;
        student.setId(id);


        repositoryStudent.put(id, student);
        return student;

    }

    @Override
    public Student read(long id) {
        Student student = repositoryStudent.get(id);
        if (student == null) {
            throw new StudentNotFoundException("Студент с id" + id + "не найден в хранилище");
        }
        return student;

    }

    @Override
    public Student update(Student student) {
        if (!repositoryStudent.containsKey(student.getId())) {
            throw new StudentNotFoundException("Студент с id" + student.getId() + "не найден в хранилище");
        }


        repositoryStudent.put(student.getId(), student);
        return student;

    }


    @Override

    public Student delete(long id) {
        Student student = repositoryStudent.remove(id);
        if (student == null) {
            throw new StudentNotFoundException("Студент с id" + id + "не найден в хранилище");
        }
        return student;
    }

    @Override
    public Collection<Student> readByAge(int age) {
        return repositoryStudent.values().stream()
                .filter(s -> s.getAge() == age)
                .collect(Collectors.toUnmodifiableList());

    }

}

