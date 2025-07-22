import java.io.*;
import java.util.*;

public class InventoryManager {
    private final List<Product> inventory = new ArrayList<>();
    private final Scanner sc = new Scanner(System.in);


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
    }

    public void deleteProduct(String productId) {
        for (Product p : new ArrayList<>(inventory)) {
            if (p.getProductId().equals(productId)) {
                inventory.remove(p);
                System.out.println("Product deleted: " );
                System.out.println( "" );
                System.out.println(p);
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


  
   
    
}