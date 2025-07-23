package supermarketkata;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.List;

import supermarketKata.PricedProduct;
import supermarketKata.Supermarket;

class SupermarketTest {

    @Test
    public void priceSummary() {
        PricedProduct pricedProduct = new PricedProduct("water", 10);
        PricedProduct pricedProduct = new PricedProduct("water", 20);
        PricedProduct pricedProduct = new PricedProduct("water", 30);
        PricedProduct pricedProduct = new PricedProduct("water", 40);
        PricedProduct pricedProduct = new PricedProduct("water", 50);
        PricedProduct pricedProduct = new PricedProduct("water", 60);
        PricedProduct pricedProduct = new PricedProduct("water", 70);
        PricedProduct pricedProduct = new PricedProduct("water", 80);
        PricedProduct pricedProduct = new PricedProduct("water", 90);
        PricedProduct pricedProduct = new PricedProduct("water", 100);


        assertEquals(Supermarket.calculateSummary(List.of(pricedProduct)), 550);
    }
}