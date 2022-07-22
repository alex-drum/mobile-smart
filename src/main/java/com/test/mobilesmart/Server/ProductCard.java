package com.test.mobilesmart.Server;

public class ProductCard {
    private static int counter = 0;

    public String getCardNumber() {
        return cardNumber;
    }

    private final String cardNumber;
    private String productName;
    private String productQuantity;
    private String productDescription;

    public ProductCard() {
        counter++;
        this.cardNumber = "Card#" + counter;

    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public void printCardInfo() {
        System.out.println(cardNumber + " " + productName + " "
                + productQuantity + " " + productDescription);
    }
}
