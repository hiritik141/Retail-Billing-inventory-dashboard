import java.io.Serializable;
import java.util.List;

public class Customer implements Serializable{
private String name;
private final List<Product> purchased_items;
private double bill;

public Customer(String name,List<Product> purchased_items, double bill) {
    this.name = name;
    this.purchased_items = purchased_items;
    this.bill = bill;
}

public String getName()
{
    return name;
}

public final List<Product> getPurchased_items() {
    return purchased_items;
}

public double getBill() {
    return bill;
}

public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\ncustomer name: ").append(name).append(":\n");
        for (Product p : purchased_items) {
            sb.append(p).append("\n");
        }
        sb.append("\nTotal bill: Rs.").append(bill).append("\n");
        sb.append("--------------------------------------------------------");
        return sb.toString();
    }
}




