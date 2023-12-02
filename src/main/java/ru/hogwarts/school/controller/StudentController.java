package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> get(@PathVariable Long id) {
        Student student = studentService.get(id);
        if (student == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(student);
    }

    @PostMapping
    public Student add(@RequestBody Student student) {
        return studentService.add(student);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Student> update(@PathVariable Long id, @RequestBody Student student) {
        Student savedStudent = studentService.update(id, student);
        if (savedStudent == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(savedStudent);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable Long id) {
        studentService.remove(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/by-age/{age}")
    public Collection<Student> getStudentsByAge(@PathVariable int age) {
        return studentService.getStudentByAge(age);
    }

    @GetMapping
    public Collection<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/byAgeBetween")
    public Collection<Student> getStudentBetweenAge(@RequestParam int min, @RequestParam int max) {
        return studentService.getStudentsBetweenAge(min, max);

    }

    @GetMapping("/students-from/{letter}")
    public Collection<String> getStudentByAlphabetAndUpperCaseFromA(@PathVariable String letter) {
        return studentService.getAllStudentsFrom(letter);
    }

    @GetMapping("/get-average-age")
    public Integer getAverageAgeOfStudents() {
        return studentService.getAverageAge();
    }


    @GetMapping("/stream")
    public List<String> getAllStudentsStream() {
        return studentService.getAllStudentsStream();
    }

    @GetMapping("/synchronized-stream")
    public List<String> getAllStudentsSynchronizedStream() {
        return studentService.getAllStudentsSynchronizedStream();
    }


}
