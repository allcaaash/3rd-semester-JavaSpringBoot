package ru.ulanov.MySpringBoot2Dbase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ulanov.MySpringBoot2Dbase.enitity.Discipline;
import ru.ulanov.MySpringBoot2Dbase.enitity.Student;
import ru.ulanov.MySpringBoot2Dbase.service.AcademicDisciplinesService;
import ru.ulanov.MySpringBoot2Dbase.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private AcademicDisciplinesService academicDisciplinesService;

    @GetMapping("/students")
    public ResponseEntity<List> allStudents() {
        List<Student> allStudents = studentService.getAllStudents();
        if (allStudents.isEmpty())
            return new ResponseEntity<>(allStudents, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(allStudents, HttpStatus.FOUND);
    }
    @GetMapping("/disciplines")
    public ResponseEntity<List> allDisciplines() {
        List<Discipline> allDisciplines = academicDisciplinesService.getAllDisciplines();
        if (allDisciplines.isEmpty())
            return new ResponseEntity<>(allDisciplines, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(allDisciplines, HttpStatus.FOUND);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable("id") int id) {
        Student student = studentService.getStudent(id);
        if (student == null)
            return new ResponseEntity<>(student, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(student, HttpStatus.FOUND);
    }

    @GetMapping("/disciplines/{id}")
    public ResponseEntity<Discipline> getDisciplines(@PathVariable("id") int id) {
        Discipline discipline = academicDisciplinesService.getDiscipline(id);
        if (discipline == null)
            return new ResponseEntity<>(discipline, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(discipline, HttpStatus.FOUND);
    }

    @PostMapping("/students")
    public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
        Student returnedStudent = studentService.saveStudent(student);
        if (returnedStudent == null)
            return new ResponseEntity<>(returnedStudent, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(returnedStudent, HttpStatus.OK);
    }

    @PostMapping("/disciplines")
    public ResponseEntity<Discipline> saveDisciplines(@RequestBody Discipline discipline) {
        Discipline returnedDiscipline = academicDisciplinesService.saveDiscipline(discipline);
        if (returnedDiscipline == null)
            return new ResponseEntity<>(returnedDiscipline, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(returnedDiscipline, HttpStatus.OK);
    }

    @PutMapping("/students")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Student updatedStudent = studentService.saveStudent(student);
        if (updatedStudent == null)
            return new ResponseEntity<>(updatedStudent, HttpStatus.CONFLICT);
        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
    }

    @PutMapping("/disciplines")
    public ResponseEntity<Discipline> updateDisciplines(@RequestBody Discipline discipline) {
        Discipline updatedDiscipline = academicDisciplinesService.saveDiscipline(discipline);
        if (updatedDiscipline == null)
            return new ResponseEntity<>(updatedDiscipline, HttpStatus.CONFLICT);
        return new ResponseEntity<>(updatedDiscipline, HttpStatus.OK);
    }

    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable("id") int id) {
        studentService.deleteStudent(id);
    }

    @DeleteMapping("/disciplines/{id}")
    public void deleteDisciplines(@PathVariable("id") int id) {
        academicDisciplinesService.deleteDiscipline(id);
    }
}
