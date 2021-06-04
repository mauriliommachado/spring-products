package br.com.mauriliomachado.portfolio.model;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class HoldingTest {

    @Test
    public void getBuilder() {
        String symbol = "MPLX";
        double price = 29.72;
        double share = 2.5;
        Date date = new Date();
        Holding.HoldingBuilder holdingBuilder = Holding.getBuilder();
        holdingBuilder.withPrice(price)
                .withShare(share)
                .withSymbol(symbol)
                .withDate(date);
        Holding holding = holdingBuilder.build();
        assertEquals(symbol, holding.getSymbol());
        assertEquals(price, holding.getPrice(), 0);
        assertEquals(share, holding.getShare(), 0);
        assertEquals(date, holding.getDate());
    }
}