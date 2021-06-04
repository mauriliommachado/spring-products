package br.com.mauriliomachado.portfolio.model;

import br.com.mauriliomachado.portfolio.model.dto.PortfolioDTO;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity(name = "portfolios")
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name="user_id")
    private User user;

    @NotBlank
    private String name;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "portfolio"
    )
    private List<Holding> holdings;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Holding> getHoldings() {
        return holdings;
    }

    public void setHoldings(List<Holding> holdings) {
        this.holdings = holdings;
    }

    public PortfolioDTO toDTO(){
        return PortfolioDTO.getBuilder()
                .withId(id)
                .withName(name)
                .withHoldings(holdings).build();
    }
}
