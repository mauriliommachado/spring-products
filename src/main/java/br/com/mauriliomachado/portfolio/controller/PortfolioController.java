package br.com.mauriliomachado.portfolio.controller;

import br.com.mauriliomachado.portfolio.error.EntityNotFoundException;
import br.com.mauriliomachado.portfolio.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@RestController
public class PortfolioController {

    @Autowired
    PortfolioService portfolioService;

    @GetMapping("/portfolio/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity findById(Principal user, @PathVariable("id") long portfolio) {
        try {
            return ResponseEntity.ok(portfolioService.findById(portfolio).toDTO());
        } catch (EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @GetMapping("/portfolio")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity findAllByUser(Principal user) {
        return ResponseEntity.ok(user.getName());
    }
}
