package br.com.mauriliomachado.portfolio.service;

import br.com.mauriliomachado.portfolio.model.RoleName;
import br.com.mauriliomachado.portfolio.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;


    public <T> Optional findByName(final RoleName roleAdmin) {
        return roleRepository.findByName(roleAdmin);
    }
}
