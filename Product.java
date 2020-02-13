// Chris Leach
import java.util.*;
import java.io.*;

public class Product implements Serializable
{
    private String productID;          // Unique product ID
    private String productName;     // Name of product
    private double salePrice;       // Sale price of product
    private int productQuantity;    // Avaliable quantity of product
    
    // Constructor
    public Product() {
        productID = "P0";
        productName = "";
        salePrice = 0;
        productQuantity = 0;
    }

    // Constructor
    public Product(String id, String name, double price) {
        productID = id;
        productName = name;
        salePrice = price;
        productQuantity = 0;
    }
    
    public String getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public int getQuantity() {
        return productQuantity;
    }

    public void setProductID(String id) {
        productID = id;
    }

    public void setProductName(String name) {
        productName = name;
    }

    public void setSalePrice(double price) {
        salePrice = price;
    }

    public void setQuantity(int quantity) {
        productQuantity = quantity;
    }
}