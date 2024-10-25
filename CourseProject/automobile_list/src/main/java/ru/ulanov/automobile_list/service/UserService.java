package ru.ulanov.automobile_list.service;

import ru.ulanov.automobile_list.dto.UserDto;
import ru.ulanov.automobile_list.entity.User;
import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);
    User findUserByEmail(String email);

    List<UserDto> findAllUsers();

    boolean updateUserRole(String email, String role);
    public User getCurrentUser();
}