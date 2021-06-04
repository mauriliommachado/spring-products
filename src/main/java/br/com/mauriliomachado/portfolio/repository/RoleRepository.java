

package br.com.mauriliomachado.portfolio.repository;

import br.com.mauriliomachado.portfolio.model.Role;
import br.com.mauriliomachado.portfolio.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}