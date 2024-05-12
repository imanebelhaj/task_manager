package ma.xproce.task_manager.service;



import ma.xproce.task_manager.dao.entites.User;
import ma.xproce.task_manager.dao.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

//implements userdetails
@Service
public class UserService {
    private final UserRepository userRepository;


    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User signUp(String username, String fullName, String email, String phone, String password, String confirmPassword, String imageUrl) {
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("Username is already taken");
        }
        if (password.length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters long");
        }
        if (!password.equals(confirmPassword)) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        User user = new User();

        user.setUsername(username);
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPassword(password);
        user.setConfirmPassword(confirmPassword);
        user.setImageUrl(imageUrl);
        user.setCreationDate(new Date()); // Set creation date
        user.setLastUpdateDate(new Date()); // Set last update date

        // Save user
        return userRepository.save(user);
    }

    public User signIn(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsernameAndPassword(username, password);
        return userOptional.orElseThrow(() -> new RuntimeException("Invalid username or password"));
    }

    public void signOut() { }


    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public User updateUser(Long userId, User newUser) {

        User existingUser = userRepository.findById(userId).orElse(null);
        if (existingUser != null) {
            if (newUser.getUsername() != null) {
                existingUser.setUsername(newUser.getUsername());
            }
            if (newUser.getPassword() != null && newUser.getConfirmPassword() != null
                    && newUser.getPassword().equals(newUser.getConfirmPassword())) {
                existingUser.setPassword(newUser.getPassword());
            }
            if (newUser.getFullName() != null && !newUser.getFullName().isEmpty()) {
                existingUser.setFullName(newUser.getFullName());
            }
            if (newUser.getEmail() != null && !newUser.getEmail().isEmpty()) {
                existingUser.setEmail(newUser.getEmail());
            }
            if (newUser.getPhone() != null && !newUser.getPhone().isEmpty()) {
                existingUser.setPhone(newUser.getPhone());
            }
            if (newUser.getImageUrl() != null && !newUser.getImageUrl().isEmpty()) {
                existingUser.setImageUrl(newUser.getImageUrl());
            }
            existingUser.setLastUpdateDate(new Date()); // Update last update date
            return userRepository.save(existingUser);
        }

        else {
            return null;
        }
    }



}
