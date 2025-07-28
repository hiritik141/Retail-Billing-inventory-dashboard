import java.io.Serializable;
import java.util.List;

public class Customer implements Serializable{
private String name;
private static List<Product> purchased_items;
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

public static List<Product> getPurchased_items() {
    return purchased_items;
}

public double getBill() {
    return bill;
}

@Override
public String toString() {
    return "Customer [name=" + name + ", bill=" + bill + ", items=" + purchased_items +"]";
}





}
