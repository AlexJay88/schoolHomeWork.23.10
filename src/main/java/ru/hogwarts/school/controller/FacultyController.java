package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public Faculty create(@RequestBody Faculty faculty) {
        return facultyService.create(faculty);

    }

    @GetMapping("/{id}")
    public Faculty read(@PathVariable long id) {
        return facultyService.read(id);
    }

    @PutMapping
    public Faculty update(@RequestBody Faculty faculty) {
        return facultyService.update(faculty);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable long id) {
        facultyService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public Collection<Faculty> readByColor(@RequestParam String color) {
        return facultyService.readByColor(color);
    }

    @GetMapping("/by-color-or-name")
    public Collection<Faculty> getFacultiesByColorOrName(@RequestParam String color, @RequestParam String name) {
        return facultyService.getFacultiesByColorOrName(color, name);
    }

    }


