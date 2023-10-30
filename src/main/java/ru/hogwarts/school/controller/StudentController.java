package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;



@RequestMapping("/students")
@RestController
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student create(@RequestBody Student student) {
        return studentService.create(student);
    }

    @GetMapping("/{id}")
    public Student read(@PathVariable long id) {
        return studentService.read(id);
    }

    @PutMapping
    public Student update(@RequestBody Student student) {
        return studentService.update(student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable long id) {
        studentService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public Collection <Student> readByAge(@PathVariable int age) {
        return studentService.readByAge(age);
    }

    @GetMapping("/age")
    public Collection<Student>readByAgeBetween(@RequestParam int minAge,@RequestParam int maxAge){
        return studentService.readByAgeBetween(minAge,maxAge);
    }
    @GetMapping("/{id}/students")
    public Faculty readStudentFaculty(@PathVariable long id) {
        return studentService.readStudentFaculty(id);
    }
    @GetMapping("/{id}/faculty")
    public Collection<Student> readByFacultyId(@PathVariable long id){
        return studentService.readByFacultyId(id);
    }

}
