package cz.tul.service;

import cz.tul.data.User;
import cz.tul.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository users) {
        this.userRepository = users;
    }

    public void create(User user) {
        userRepository.save(user);
    }

    public boolean exists(String username) {
        return userRepository.existsByUsername(username);
    }

    public List<User> getAllUsers() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public User getUser(String username) {
        return userRepository.findByUsername(username).get();
    }

    public void delete(String username) {
        userRepository.deleteByUsername(username);
    }
}
