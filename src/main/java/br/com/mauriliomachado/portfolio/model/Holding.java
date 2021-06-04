package br.com.mauriliomachado.portfolio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity(name = "holdings")
public class Holding {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    private String symbol;
    @NotNull
    private double price;
    @NotNull
    private double share;

    @Temporal(TemporalType.TIMESTAMP)
    Date date;

    @ManyToOne
    @JoinColumn(name="portfolio_id")
    @JsonIgnore
    private Portfolio portfolio;

    private Holding(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    private void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getPrice() {
        return price;
    }

    private void setPrice(double price) {
        this.price = price;
    }

    public double getShare() {
        return share;
    }

    private void setShare(double share) {
        this.share = share;
    }

    public static HoldingBuilder getBuilder(){
        return new HoldingBuilder();
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public static class HoldingBuilder{

        private String symbol;
        private double price;
        private double share;
        private Date date;

        public HoldingBuilder withSymbol(String symbol){
            this.symbol = symbol;
            return this;
        }

        public HoldingBuilder withPrice(double price) {
            this.price = price;
            return this;
        }

        public HoldingBuilder withShare(double share){
            this.share = share;
            return this;
        }

        public HoldingBuilder withDate(Date date){
            this.date = date;
            return this;
        }

        public Holding build(){
            Holding holding = new Holding();
            holding.setPrice(price);
            holding.setShare(share);
            holding.setSymbol(symbol);
            holding.setDate(date);
            return holding;
        }
    }
}
