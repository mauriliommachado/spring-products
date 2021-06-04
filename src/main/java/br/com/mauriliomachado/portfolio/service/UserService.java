
package br.com.mauriliomachado.portfolio.service;

import br.com.mauriliomachado.portfolio.model.User;
import br.com.mauriliomachado.portfolio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User findByUsername(final String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public boolean existsByUsername(final String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(final String email) {
        return userRepository.existsByEmail(email);
    }

    public void save(final User user) {
        userRepository.save(user);
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
