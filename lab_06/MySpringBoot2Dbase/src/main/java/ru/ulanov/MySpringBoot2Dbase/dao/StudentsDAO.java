package ru.ulanov.MySpringBoot2Dbase.dao;

import org.springframework.stereotype.Repository;
import ru.ulanov.MySpringBoot2Dbase.enitity.Student;

import java.util.List;

@Repository
public interface StudentsDAO {
    List<Student> getAllStudents();
    Student saveStudent(Student student);
    Student getStudent(int id);
    void deleteStudent(int id);
}
