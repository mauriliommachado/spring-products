package br.com.mauriliomachado.products.controller;

import br.com.mauriliomachado.products.model.User;
import br.com.mauriliomachado.products.security.JwtProvider;
import br.com.mauriliomachado.products.security.message.JwtResponse;
import br.com.mauriliomachado.products.security.message.LoginForm;
import br.com.mauriliomachado.products.security.message.Message;
import br.com.mauriliomachado.products.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class UserController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder encoder;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity findAll(){
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping("/users/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = userService.findByUsername(loginRequest.getUsername());
        String jwt = jwtProvider.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt, user.getRole()));
    }

    @PostMapping("/users")
    public ResponseEntity registerUser(@Valid @RequestBody User user) throws URISyntaxException {
        if (userService.existsByUsername(user.getUsername())) {
            return new ResponseEntity<>(
                    new Message("Fail -> Email is already in use!"),
                    HttpStatus.BAD_REQUEST
            );
        }

        if (userService.existsByEmail(user.getEmail())) {
            return new ResponseEntity<>(
                    new Message("Fail -> Email is already in use!"),
                    HttpStatus.BAD_REQUEST
            );
        }
        user.setPassword(encoder.encode(user.getPassword()));
        try {
            userService.save(user);
        } catch (ConstraintViolationException ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getConstraintViolations());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.created(new URI(user.getId().toString())).body(null);
    }
}
