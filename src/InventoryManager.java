import java.io.*;
import java.util.*;

public class InventoryManager {
    private final List<Product> inventory = new ArrayList<>();
    private final Scanner sc = new Scanner(System.in);
    private final static String data_file = "inventory.ser";
    private final List<Product> sold_items = new ArrayList<>();
    private final List<Customer> customerList = new ArrayList<>();

     public InventoryManager()
    {
        loadInventory();
    }



    public void addProduct() {
        while (true) {
            
        
        System.out.println("\n Add New Product ");
        System.out.print("Enter product name:   (or you can write 'done' if added)  ");
        String name = sc.nextLine();
        if (name.equalsIgnoreCase("done"))
        {break;}

        System.out.print("Enter price: ");
        double price = sc.nextDouble();

        System.out.print("Enter quantity: ");
        int quantity = sc.nextInt();
        sc.nextLine();

        System.out.println("Available Categories:");
        for (Category c : Category.values()) {
            System.out.println("- " + c);
        }

        Category category = null;
        while (category == null) {
            System.out.print("Enter category name from above: ");
            String input = sc.nextLine().trim().toUpperCase();
            try {
                category = Category.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println(" Invalid category. Please try again.");
            }
        }

        Product p = new Product(name, price, quantity, category);
        inventory.add(p);
       
        
    }
    saveInventory();
    System.out.println("\nproduct added\n");
    for (Product p : inventory)
    {
        System.out.println(p.getName());
    }
}

    public void deleteProduct(String productId) {
        for (Product p : new ArrayList<>(inventory)) {
            if (p.getProductId().equals(productId)) {
                inventory.remove(p);
                System.out.println("\nProduct deleted: " );
                System.out.println( "" );
                System.out.println(p);
                saveInventory();
                return;
              
            }
        }
        System.out.println("Product not found.");
    }

    public void updateProduct(String productId) {
        for (Product p : inventory) {
            if (p.getProductId().equals(productId)) {
                System.out.print("Enter new price: ");
                double newPrice = sc.nextDouble();
                System.out.print("Enter new quantity: ");
                int newQty = sc.nextInt();
                sc.nextLine();
                p.setPrice(newPrice);
                p.setQuantity(newQty);
                System.out.println("\nProduct updated: " + p);
                saveInventory();
                return;
                
            }
        }
        System.out.println("Product not found.");
    }

    public void displayAllProducts() {
        System.out.println("\n Inventory Items:");
        for (Product p :  inventory) {
            System.out.println("  - " + p);
        }
    }

    public void sellToCustomer() {

    System.out.print("Enter customer name: ");
    String customerName = sc.nextLine();

    List<Product> purchasedItems = new ArrayList<>();
    double totalBill = 0;

    while (true) {
        System.out.print("\nEnter product ID to sell (or type 'done' to end): ");
        String productID = sc.nextLine();
        if (productID.equalsIgnoreCase("done")) break;

        boolean found = false;
        for (Product p : new ArrayList<>(inventory)) {
            if (p.getProductId().equals(productID)) {
                System.out.print("Enter quantity to sell: ");
                int qtyToSell = Integer.parseInt(sc.nextLine());

                if (qtyToSell <= 0 || qtyToSell > p.getQuantity()) {
                    System.out.println(" Invalid quantity. Available: " + p.getQuantity());
                    found = true;
                    break;
                }

                
                p.setQuantity(p.getQuantity() - qtyToSell);
                if (p.getQuantity() == 0) {
                    inventory.remove(p);
                }

                
             Product soldProduct = new Product(
                    p.getProductId(),
                    p.getName(),
                    p.getPrice(),
                    qtyToSell,
                    p.getCategory()
                );
                

               
                sold_items.add(soldProduct);
                purchasedItems.add(soldProduct);
                totalBill += p.getPrice() * qtyToSell;
                

                System.out.println("\nSold: " + soldProduct);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println(" wrong product ID: " + productID);
        }
    }

    if (!purchasedItems.isEmpty()) {
        Customer customer = new Customer(customerName, purchasedItems, totalBill);
        customerList.add(customer);
       System.out.println("\nPurchase Summary for " + customerName + ":");
for (Product p : purchasedItems) {
    System.out.println(p); 
}
System.out.println("\nTotal bill: Rs." + totalBill);

    }

    saveInventory();
}

    public void display_sold_product()
    {
        System.out.println("\n     sold products     ");
        for (Product p : sold_items)
        {
            
            System.out.println(p);
        }
    } 

      public void saveInventory()
    {
        try(FileOutputStream fi = new FileOutputStream(data_file);
        ObjectOutputStream si = new ObjectOutputStream(fi))
        {
            si.writeObject(inventory);
            si.writeObject(sold_items);
            si.writeObject(customerList);
            si.writeInt(Product.getCounter());

        }
        catch (Exception e) {
            System.err.println("error saving inventory");
                }
    }

    public void loadInventory()
    {
        File file = new File(data_file);
        if (!file.exists())
        {return;}
        try (FileInputStream fi = new FileInputStream(data_file);
        ObjectInputStream re = new ObjectInputStream(fi))
        {
        List<Product> load_list =  (List<Product>) re.readObject();  
        inventory.clear();
        inventory.addAll(load_list);
        
        List<Product> sold_list = (List<Product>) re.readObject();
        sold_items.clear();
        sold_items.addAll(sold_list);

        List<Customer> customerlistRead = (List<Customer>) re.readObject();
        customerList.clear();
        customerList.addAll(customerlistRead);

        if (inventory.isEmpty() && sold_items.isEmpty())
        {
            Product.setCounter(100);
        }
        else
        {
        int readCounter = re.readInt();
        Product.setCounter(readCounter);
        }
    }
    catch (Exception e) {
        System.out.println("error loading inventory");
    }
    }

}