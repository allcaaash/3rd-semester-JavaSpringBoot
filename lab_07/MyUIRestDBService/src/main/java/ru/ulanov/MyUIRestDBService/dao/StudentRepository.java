package ru.ulanov.MyUIRestDBService.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ulanov.MyUIRestDBService.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

}
