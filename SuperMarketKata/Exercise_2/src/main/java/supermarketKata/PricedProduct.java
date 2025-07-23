package supermarketKata;

public class PricedProduct {
    String productName;
    int productPrice;
    int offer;
    
    public PricedProduct(String productName, int productPrice, int offer) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.offer = offer;
    }

    public int getProductPrice() {
        return productPrice - (productPrice * offer / 100);
    }
}
