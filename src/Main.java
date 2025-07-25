import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InventoryManager manager = new InventoryManager();
        Scanner sc = new Scanner(System.in);
        /* */

        while (true) {
            System.out.println("\nRetail Billing & Inventory Dashboard ");
            System.out.println("1. Add Product");
            System.out.println("2. Delete Product");
            System.out.println("3. Update Product");
            System.out.println("4. View Inventory");
            System.out.println("5. Item that you want to sell");
             System.out.println("6. Sold items list");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 :
                {
                 manager.addProduct();
                System.out.println("\nPress Enter to return to Home");
                sc.nextLine(); 
                break;
                } 
                case 2 :
                {
                    System.out.print("Enter Product ID to delete: ");
                    String deleteId = sc.nextLine();
                    manager.deleteProduct(deleteId);
                    System.out.println("\nPress Enter to return to Home");
                    sc.nextLine();  
                    break;
                }
                case 3 : 
                {
                    System.out.print("Enter Product ID to update: ");
                    String updateId = sc.nextLine();
                    manager.updateProduct(updateId);
                    System.out.println("\nPress Enter to return to Home");
                    sc.nextLine();  
                    break;
                }
                case 4 :
                {
                manager.displayAllProducts();
                System.out.println("\nPress Enter to return to Home");
                sc.nextLine(); 
                break;
                }

                case 5 :
                {
                    System.out.print("enter the productID that you are selling ");
                    String pid = sc.nextLine();
                    manager.sold_product(pid);
                    System.out.println("press enter to return to home");
                    sc.nextLine();
                    break;
                }
                case 6:
                {
                    manager.display_sold_product();
                    System.out.println("press enter to return to home");
                    sc.nextLine();
                    break;

                }

                case 7 :
                {
                    System.out.println("Exiting");
                    return;
                }
                default :
                System.out.println("Invalid option");
                break;
            }
        }
    }
}
