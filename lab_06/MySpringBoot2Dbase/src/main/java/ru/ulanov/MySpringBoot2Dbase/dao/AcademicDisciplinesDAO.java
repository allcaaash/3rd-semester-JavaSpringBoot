package ru.ulanov.MySpringBoot2Dbase.dao;

import org.springframework.stereotype.Repository;
import ru.ulanov.MySpringBoot2Dbase.enitity.Discipline;

import java.util.List;

@Repository
public interface AcademicDisciplinesDAO {
    List<Discipline> getAllDisciplines();
    Discipline saveDiscipline(Discipline discipline);
    Discipline getDiscipline(int id);
    void deleteDiscipline(int id);
}
