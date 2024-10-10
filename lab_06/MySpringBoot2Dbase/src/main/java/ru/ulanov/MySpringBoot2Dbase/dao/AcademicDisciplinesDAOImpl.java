package ru.ulanov.MySpringBoot2Dbase.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.ulanov.MySpringBoot2Dbase.enitity.Discipline;

import java.util.List;
@Slf4j
@Repository
public class AcademicDisciplinesDAOImpl implements AcademicDisciplinesDAO {
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Discipline> getAllDisciplines() {
        Query query = entityManager.createQuery("from Discipline");
        List<Discipline> allDisciplines = query.getResultList();
        log.info("getAllDisciplines" + allDisciplines);
        return allDisciplines;
    }

    @Override
    public Discipline saveDiscipline(Discipline discipline) { return entityManager.merge(discipline); }

    @Override
    public  Discipline getDiscipline(int id) { return entityManager.find(Discipline.class, id); }

    @Override
    public void deleteDiscipline(int id) {
        Query query = entityManager.createQuery("delete from Discipline "
                + " where id =:disciplineId");
        query.setParameter("disciplineId", id);
        query.executeUpdate();
    }
}
