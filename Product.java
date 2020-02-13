// Chris Leach

public class Product
{
    private int productID;          // Unique product ID
    private String productName;     // Name of product
    private double salePrice;       // Sale price of product
    private int productQuantity;    // Avaliable quantity of product
    
    // Constructor
    public Product() {
        productID = 0;
        productName = "";
        salePrice = 0;
        productQuantity = 0;
    }

    // Constructor
    public Product(int id, String name, double price) {
        productID = id;
        productName = name;
        salePrice = price;
        productQuantity = 0;
    }
    
    public int getProductID() {
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

    public void setProductID(int id) {
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