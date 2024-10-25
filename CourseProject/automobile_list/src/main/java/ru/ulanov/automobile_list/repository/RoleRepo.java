package ru.ulanov.automobile_list.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ulanov.automobile_list.entity.Role;

public interface RoleRepo  extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
