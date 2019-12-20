package br.com.mauriliomachado.products;

import br.com.mauriliomachado.products.model.Role;
import br.com.mauriliomachado.products.model.RoleName;
import br.com.mauriliomachado.products.repository.RoleRepository;
import br.com.mauriliomachado.products.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DropshippingApplication {

	public static void main(String[] args) {
		SpringApplication.run(DropshippingApplication.class, args);
	}

	@Bean
	CommandLineRunner init(RoleRepository roleRepository, UserService userService) {

		return args -> {
			if (!roleRepository.findByName(RoleName.ROLE_USER).isPresent()) {
				Role newAdminRole = new Role();
				newAdminRole.setName(RoleName.ROLE_USER);
				roleRepository.save(newAdminRole);
			}
			if (!roleRepository.findByName(RoleName.ROLE_ADMIN).isPresent()) {
				Role newAdminRole = new Role();
				newAdminRole.setName(RoleName.ROLE_ADMIN);
				roleRepository.save(newAdminRole);
			}
		};

	}

}
