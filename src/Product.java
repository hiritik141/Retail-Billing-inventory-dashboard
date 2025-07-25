import java.io.Serializable;

public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private static int count = 100;

    private final String productId;
    private String name;
    private double price;
    private int quantity;
    private final Category category;

    public Product(String name, double price, int quantity, Category category) {
        this.productId = "P" + count++;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

     public static int getCounter() {
        return count;
    }

    public static void setCounter(int c) {
        count = c;
    }

    @Override
    public String toString() {
        return  "\nProduct ID: " + productId + "\nName: " +name +"\nPrice: Rs." + price +"\nQuantity: " + quantity +
"\nCategory: " + category;
    }
}