package supermarketkata;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import supermarketKata.ClientSale;
import supermarketKata.PricedProduct;
import supermarketKata.Supermarket;

class SupermarketTest {

    @Test
    public void priceSummary() {
        List<PricedProduct> pricedProductList = new ArrayList<>();

        pricedProductList.add(new PricedProduct("water", 10,0));
        pricedProductList.add(new PricedProduct("water 1", 20,0));
        pricedProductList.add(new PricedProduct("water 2", 30,0));
        pricedProductList.add(new PricedProduct("water 3", 40,0));
        pricedProductList.add(new PricedProduct("water 4", 50,0));
        pricedProductList.add(new PricedProduct("water 5", 60,0));
        pricedProductList.add(new PricedProduct("water 6", 70,0));
        pricedProductList.add(new PricedProduct("water 7", 80,0));
        pricedProductList.add(new PricedProduct("water 8", 90,0));
        pricedProductList.add(new PricedProduct("water 9", 100,0));
        ClientSale clientSale = new ClientSale("Carl", pricedProductList);
        List<PricedProduct> ddd = new ArrayList<>(pricedProductList);
        ddd.add(new PricedProduct("water 9", 40,7));

        ClientSale clientSale2 = new ClientSale("Carl2", ddd);


        assertEquals(588, Supermarket.calculateSummary(List.of(clientSale, clientSale2)).get(0));
    }
}