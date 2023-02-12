package com.example.quotesite.service;

import com.example.quotesite.domain.Role;
import com.example.quotesite.domain.User;
import com.example.quotesite.repository.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getUserById(Long userId) {
        return userRepo.findById(userId).orElseThrow();
    }

    @Transactional
    public User insert(User user) {
        user.setDateCreation(LocalDateTime.now());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRoles() == null){
            user.setRoles(Collections.singleton(Role.USER));
        }
        return userRepo.save(user);
    }

    @Transactional
    public void updateUser(Long userId, User user) {
        User updateUser = getUserById(userId);
        updateUser.setUserName(user.getUserName());
        updateUser.setEmail(user.getEmail());
        updateUser.setPassword(passwordEncoder.encode(user.getPassword()));
        updateUser.setDateCreation(LocalDateTime.now());
        updateUser.setRoles(user.getRoles());
        userRepo.save(updateUser);
    }

    public void deleteUser(Long userId) {
        userRepo.deleteById(userId);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepo.findByUserName(userName);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }
}
