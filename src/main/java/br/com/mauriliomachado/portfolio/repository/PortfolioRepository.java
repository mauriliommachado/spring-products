
package br.com.mauriliomachado.portfolio.repository;

import br.com.mauriliomachado.portfolio.model.Portfolio;
import br.com.mauriliomachado.portfolio.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {

    Portfolio findByIdAndUserId(String id, Long userId);
}