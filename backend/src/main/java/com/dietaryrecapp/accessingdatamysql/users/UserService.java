package com.dietaryrecapp.accessingdatamysql.users;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AuthoritiesRepository authoritiesRepository;

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Integer user_id) {
        return userRepository.findById(user_id);
    }

    public User addUser(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        User userSaved = userRepository.save(user);
        Authorities authorities = new Authorities();
        authorities.setUsername(user.getUsername());
        authorities.setAuthority("ROLE_USER");
        authoritiesRepository.save(authorities);
        return userSaved;
    }

    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public User editUser(User user, Integer user_id) {
        User editedUser = userRepository.findUserById(user_id);
        editedUser.setEmail(user.getEmail());
        editedUser.setHeight(user.getHeight());
        editedUser.setWeight(user.getWeight());
        return userRepository.save(editedUser);
    }

}
