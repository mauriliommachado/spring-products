package br.com.mauriliomachado.portfolio.model.dto;

import br.com.mauriliomachado.portfolio.model.Holding;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class PortfolioDTO {
    private Long id;
    private String name;
    private List<Holding> holdings;

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public List<Holding> getHoldings() {
        return holdings;
    }

    private void setHoldings(List<Holding> holdings) {
        this.holdings = holdings;
    }

    public static PortfolioBuilder getBuilder() {
        return new PortfolioBuilder();
    }

    public static class PortfolioBuilder {
        private Long id;
        private String name;
        private List<Holding> holdings;

        public PortfolioBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public PortfolioBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public PortfolioBuilder withHoldings(List<Holding> holdings) {
            this.holdings = new ArrayList<>();
            for (Holding holding : holdings) {
                if (this.holdings.stream().anyMatch(aux -> aux.getSymbol().equals(holding.getSymbol()))) {
                    continue;
                }
                Supplier<Stream<Holding>> streamSupplier
                        = () -> holdings.stream().filter(aux -> aux.getSymbol().equals(holding.getSymbol()));

                Holding.HoldingBuilder holdingBuilder = Holding.getBuilder()
                        .withSymbol(holding.getSymbol());
                holdingBuilder.withPrice(streamSupplier.get().mapToDouble(x -> x.getPrice()).sum() / streamSupplier.get().count());
                holdingBuilder.withShare(streamSupplier.get().mapToDouble(x -> x.getShare()).sum());

                this.holdings.add(holdingBuilder.build());
            }
            return this;
        }

        public PortfolioDTO build() {
            PortfolioDTO dto = new PortfolioDTO();
            dto.setId(this.id);
            dto.setName(this.name);
            dto.setHoldings(this.holdings);
            return dto;
        }
    }
}
