package supermarketKata;

import java.util.List;

public class ClientSale {
    String name;
    List<PricedProduct> pricedProducts;

    public ClientSale(String name, List<PricedProduct> pricedProducts) {
        this.name = name;
        this.pricedProducts = pricedProducts;
    }
}
