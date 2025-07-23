package supermarketKata;

import java.util.List;
public class Supermarket {
    public static int calculateSummary(List<PricedProduct> productList){

        return productList.stream()
                .mapToInt(PricedProduct::getProductPrice)
                .sum();
    }
}