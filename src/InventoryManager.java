import java.io.*;
import java.util.*;

public class InventoryManager {
    private final List<Product> inventory = new ArrayList<>();
    private final Scanner sc = new Scanner(System.in);
    private final static String data_file = "inventory.ser";
    private final List<Product> sold_items = new ArrayList<>();

     public InventoryManager()
    {
        loadInventory();
    }



    public void addProduct() {
        System.out.println("\n Add New Product ");
        System.out.print("Enter product name: ");
        String name = sc.nextLine();

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
        System.out.println("\nProduct added: " + p);
        saveInventory();
    }

    public void deleteProduct(String productId) {
        for (Product p : new ArrayList<>(inventory)) {
            if (p.getProductId().equals(productId)) {
                inventory.remove(p);
                System.out.println("Product deleted: " );
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
                System.out.println("Product updated: " + p);
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

     public void sold_product (String productID)
    {
        for (Product p : new ArrayList<>(inventory))
        {
            if (p.getProductId().equals(productID))
            {
            sold_items.add(p);
            inventory.remove(p);
            System.out.println("product added to sold list\n"+  p);
            saveInventory();
            return;
            }
        }
        System.out.println("invalid product id or product not found");
    }
    public void display_sold_product()
    {
        System.out.println("     sold products     ");
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