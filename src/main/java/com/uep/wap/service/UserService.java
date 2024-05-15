package com.uep.wap.service;

import com.uep.wap.dto.UserDTO;
import com.uep.wap.model.User;
import com.uep.wap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Convert User to UserDTO
    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getUserId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setMiddleName(user.getMiddleName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole(user.getRole());
        return userDTO;
    }

    // Convert UserDTO to User
    private User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setUserId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setMiddleName(userDTO.getMiddleName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setRole(userDTO.getRole());
        return user;
    }

    // Creating a new user
    @Transactional
    public UserDTO createUser(UserDTO userDTO) {
        User newUser = convertToEntity(userDTO);
        return convertToDTO(userRepository.save(newUser));
    }

    // Getting a user by ID
    public UserDTO getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + userId + " not found"));
        return convertToDTO(user);
    }

    // Updating user data
    @Transactional
    public UserDTO updateUser(Long userId, UserDTO userDTO) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + userId + " not found"));
        existingUser.setFirstName(userDTO.getFirstName());
        existingUser.setMiddleName(userDTO.getMiddleName());
        existingUser.setLastName(userDTO.getLastName());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setPassword(userDTO.getPassword());
        existingUser.setRole(userDTO.getRole());
        return convertToDTO(userRepository.save(existingUser));
    }

    // Deleting a user
    @Transactional
    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User with ID " + userId + " not found");
        }
        userRepository.deleteById(userId);
    }

    // Getting users by role
    public List<UserDTO> getUsersByRole(User.UserRole role) {
        return userRepository.findByRole(role.toString()).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Getting users by last name
    public List<UserDTO> getUsersByLastName(String lastName) {
        return userRepository.findByLastName(lastName).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Getting users by first name and last name
    public List<UserDTO> getUsersByFirstNameAndLastName(String firstName, String lastName) {
        return userRepository.findByFirstNameAndLastName(firstName, lastName).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Getting a user by email
    public UserDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return convertToDTO(user);
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}