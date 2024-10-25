package ru.ulanov.automobile_list.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ulanov.automobile_list.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
