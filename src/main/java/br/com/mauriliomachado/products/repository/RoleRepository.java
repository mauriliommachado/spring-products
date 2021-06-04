

package br.com.mauriliomachado.products.repository;

import br.com.mauriliomachado.products.model.Role;
import br.com.mauriliomachado.products.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}