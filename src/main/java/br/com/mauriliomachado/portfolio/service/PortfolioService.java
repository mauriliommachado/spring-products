
package br.com.mauriliomachado.portfolio.service;

import br.com.mauriliomachado.portfolio.error.EntityNotFoundException;
import br.com.mauriliomachado.portfolio.model.Portfolio;
import br.com.mauriliomachado.portfolio.repository.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortfolioService {

    @Autowired
    PortfolioRepository portfolioRepository;

    public Portfolio findById(final Long id) throws EntityNotFoundException {
        return portfolioRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Portfolio not found"));
    }
    
    public void save(final Portfolio portfolio) {
        portfolioRepository.save(portfolio);
    }

    public void deleteAll() {
        portfolioRepository.deleteAll();
    }

    public List<Portfolio> findAll() {
        return portfolioRepository.findAll();
    }
}
