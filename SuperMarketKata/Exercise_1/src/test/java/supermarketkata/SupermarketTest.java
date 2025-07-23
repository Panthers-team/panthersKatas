package supermarketkata;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import supermarketKata.PricedProduct;
import supermarketKata.Supermarket;

class SupermarketTest {

    @Test
    public void priceSummary() {
        List<PricedProduct> pricedProductList = new ArrayList<>();

        pricedProductList.add(new PricedProduct("water", 10));
        pricedProductList.add(new PricedProduct("water 1", 20));
        pricedProductList.add(new PricedProduct("water 2", 30));
        pricedProductList.add(new PricedProduct("water 3", 40));
        pricedProductList.add(new PricedProduct("water 4", 50));
        pricedProductList.add(new PricedProduct("water 5", 60));
        pricedProductList.add(new PricedProduct("water 6", 70));
        pricedProductList.add(new PricedProduct("water 7", 80));
        pricedProductList.add(new PricedProduct("water 8", 90));
        pricedProductList.add(new PricedProduct("water 9", 100));



        assertEquals(550, Supermarket.calculateSummary(pricedProductList));
    }
}