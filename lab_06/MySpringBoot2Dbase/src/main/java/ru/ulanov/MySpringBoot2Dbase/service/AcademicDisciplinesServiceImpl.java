package ru.ulanov.MySpringBoot2Dbase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ulanov.MySpringBoot2Dbase.dao.AcademicDisciplinesDAO;
import ru.ulanov.MySpringBoot2Dbase.enitity.Discipline;

import java.util.List;

@Service
public class AcademicDisciplinesServiceImpl implements AcademicDisciplinesService {
    @Autowired
    private AcademicDisciplinesDAO academicDisciplinesDAO;

    @Override
    @Transactional
    public List<Discipline> getAllDisciplines() { return academicDisciplinesDAO.getAllDisciplines(); }

    @Override
    @Transactional
    public Discipline saveDiscipline(Discipline discipline) { return academicDisciplinesDAO.saveDiscipline(discipline); }

    @Override
    @Transactional
    public Discipline getDiscipline(int id) { return academicDisciplinesDAO.getDiscipline(id); }

    @Override
    @Transactional
    public void deleteDiscipline(int id) { academicDisciplinesDAO.deleteDiscipline(id); }
}
