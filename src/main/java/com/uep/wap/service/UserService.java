package com.uep.wap.service;

import com.uep.wap.model.User;
import com.uep.wap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Dodawanie nowego użytkownika
    @Transactional
    public User createUser(String firstName, String middleName, String lastName, String email, String password, User.UserRole role) {
        User newUser = new User(firstName, middleName, lastName, email, password, role);
        return userRepository.save(newUser);
    }

    // Pobieranie użytkownika po ID
    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    // Aktualizacja danych użytkownika
    @Transactional
    public User updateUser(Long userId, String firstName, String middleName, String lastName, String email, String password, User.UserRole role) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + userId + " not found"));
        existingUser.setFirstName(firstName);
        existingUser.setMiddleName(middleName);
        existingUser.setLastName(lastName);
        existingUser.setEmail(email);
        existingUser.setPassword(password);
        existingUser.setRole(role);
        return userRepository.save(existingUser);
    }

    // Usuwanie użytkownika
    @Transactional
    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User with ID " + userId + " not found");
        }
        userRepository.deleteById(userId);
    }

    // Pobieranie użytkowników po roli
    public List<User> getUsersByRole(User.UserRole role) {
        return userRepository.findByRole(role.toString());
    }

    // Pobieranie użytkowników po nazwisku
    public List<User> getUsersByLastName(String lastName) {
        return userRepository.findByLastName(lastName);
    }

    // Pobieranie użytkowników po imieniu i nazwisku
    public List<User> getUsersByFirstNameAndLastName(String firstName, String lastName) {
        return userRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    // Pobieranie użytkownika po adresie email
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
