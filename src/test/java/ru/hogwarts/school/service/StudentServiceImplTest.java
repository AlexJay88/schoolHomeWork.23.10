package ru.hogwarts.school.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.RepositoryStudent;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith((MockitoExtension.class))
public class StudentServiceImplTest {

   @Mock
    RepositoryStudent repositoryStudent;
   @InjectMocks
   StudentServiceImpl service;
   Student student=new Student(1L,"Гарри",15);

    @Test
    void createStudent() {
        when(repositoryStudent.save(student)).thenReturn(student);
     Student result = service.create(student);
     assertEquals(student,result);
    }


    @Test
    void readShouldReturnStudent() {
     when(repositoryStudent.findById(student.getId()))
             .thenReturn(Optional.of(student));
     Student result=service.read(student.getId());
     assertEquals(student,result);

    }
 @Test
 void readShouldThrowExceptionWhenStudentNotInDB() {
  when(repositoryStudent.findById(student.getId()))
          .thenReturn(Optional.empty());

  assertThrows(StudentNotFoundException.class,
          ()->service.read(student.getId()));



 }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void readByAge() {
    }

    @Test
    void testCreate() {
    }

    @Test
    void testRead() {
    }

    @Test
    void testUpdate() {
    }

    @Test
    void testReadByAge() {
    }
}
