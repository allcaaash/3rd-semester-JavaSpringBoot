package ru.ulanov.automobile_list.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ulanov.automobile_list.entity.Automobile;
import ru.ulanov.automobile_list.entity.User;

import java.util.List;

@Repository
public interface AutomobileRepo extends JpaRepository<Automobile, Long> {
    List<Automobile> findByUserId(Long userId);
}
