package ru.ulanov.automobile_list.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ulanov.automobile_list.dto.UserDto;
import ru.ulanov.automobile_list.entity.Role;
import ru.ulanov.automobile_list.entity.User;
import ru.ulanov.automobile_list.repository.RoleRepo;
import ru.ulanov.automobile_list.repository.UserRepo;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepository;
    private final RoleRepo roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl (UserRepo userRepository,
                            RoleRepo roleRepository,
                            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getFirstName() + " " + userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Role role = roleRepository.findByName("ROLE_READONLY");
        if (role == null) {
            role = checkRoleExit();
        }
        user.setRoles(Collections.singletonList(role));
        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) { return userRepository.findByEmail(email); }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(this::mapToUserDto)
                .toList();
    }

    @Override
    public boolean updateUserRole(String email, String roleName) {
        User user = userRepository.findByEmail(email);
        if (user == null)
            return false;

        Role role = roleRepository.findByName(roleName);
        if (role == null) {
            role = checkRoleExit();
        }
        if (!user.getRoles().contains(role)) {
            user.getRoles().clear(); // очищаем старые роли (если нужно)
            user.getRoles().add(role);
        }
        userRepository.save(user);

        return true;
    }

    private Role checkRoleExit() {
        Role role = new Role();
        role.setName("ROLE_READONLY");

        return role;
    }

    private UserDto mapToUserDto(User user) {
        UserDto userDto = new UserDto();
        String[] str = user.getName().split(" ");
        userDto.setFirstName(str[0]);
        userDto.setLastName(str[1]);
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRoles().get(0).getName());

        return userDto;
    }

    @Override
    public User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String email = ((UserDetails) principal).getUsername();
            return userRepository.findByEmail(email);
        }
        return null;
    }
}