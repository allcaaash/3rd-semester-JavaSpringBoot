package ru.ulanov.MySpringBoot2Dbase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ulanov.MySpringBoot2Dbase.dao.StudentsDAO;
import ru.ulanov.MySpringBoot2Dbase.enitity.Student;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentsDAO studentsDAO;

    @Override
    @Transactional
    public List<Student> getAllStudents() { return studentsDAO.getAllStudents(); }

    @Override
    @Transactional
    public Student saveStudent(Student student) { return studentsDAO.saveStudent(student); }

    @Override
    @Transactional
    public Student getStudent(int id) { return studentsDAO.getStudent(id); }

    @Override
    @Transactional
    public void deleteStudent(int id) { studentsDAO.deleteStudent(id); }
}
 